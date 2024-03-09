package com.jab.gerenciapedidoapi.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jab.gerenciapedidoapi.model.ItemPedido;
import com.jab.gerenciapedidoapi.repository.ItemPedidoRepository;
import com.jab.gerenciapedidoapi.service.ItemPedidoService;

@RestController
@RequestMapping("/itens-pedidos")
public class ItemPedidoResource {
	
	@Autowired
	private ItemPedidoService itemPedidoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@GetMapping
	public ResponseEntity<List<ItemPedido>> listar() {
		List<ItemPedido> itensPedidos = itemPedidoService.listar();
		return ResponseEntity.ok(itensPedidos);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemPedido> criar(@RequestBody ItemPedido itemPedido) {
		itemPedido = itemPedidoService.salvar(itemPedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(itemPedido);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> buscarPeloId(@PathVariable String id) {
		Optional<ItemPedido> itemPedido = itemPedidoService.buscarPorId(id);
		return itemPedido.isPresent() ? ResponseEntity.ok(itemPedido.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		itemPedidoRepository.deleteById(UUID.fromString(id));
	}
	
	@PutMapping
	public ResponseEntity<ItemPedido> atualizar(@RequestBody ItemPedido itemPedido) {
		try {
			ItemPedido itemPedidoSalvo = itemPedidoService.atualizar(itemPedido);
			return ResponseEntity.ok(itemPedidoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
