package com.jab.gerenciapedidoapi.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jab.gerenciapedidoapi.model.ItemPedido;
import com.jab.gerenciapedidoapi.repository.ItemPedidoRepository;

@RestController
@RequestMapping("/itens-pedidos")
public class ItemPedidoResource {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@GetMapping
	public ResponseEntity<List<ItemPedido>> listar() {
		List<ItemPedido> itensPedidos = itemPedidoRepository.findAll();
		return ResponseEntity.ok(itensPedidos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> buscarPeloId(@PathVariable String id) {
		Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(UUID.fromString(id));
		return itemPedido.isPresent() ? ResponseEntity.ok(itemPedido.get()) : ResponseEntity.notFound().build();
	}
	
}
