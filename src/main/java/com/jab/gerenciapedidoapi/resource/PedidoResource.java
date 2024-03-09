package com.jab.gerenciapedidoapi.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.jab.gerenciapedidoapi.model.Pedido;
import com.jab.gerenciapedidoapi.repository.PedidoRepository;
import com.jab.gerenciapedidoapi.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listar() {
		List<Pedido> pedidos = pedidoService.listar();
		return ResponseEntity.ok(pedidos);
	}
	
	@PostMapping
	public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
		Pedido pedidoSalvo = pedidoService.salvar(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPeloId(@PathVariable String id) {
		Optional<Pedido> pedido = pedidoService.buscarPorId(id);
		return pedido.isPresent() ? ResponseEntity.ok(pedido.get()) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String id) {
		pedidoRepository.deleteById(UUID.fromString(id));
	}
	
	@PutMapping
	public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido) {
		try {
			Pedido pedidoSalvo = pedidoService.atualizar(pedido);
			return ResponseEntity.ok(pedidoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
