package com.jab.gerenciapedidoapi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jab.gerenciapedidoapi.model.Item;
import com.jab.gerenciapedidoapi.service.ItemService;

@RestController
@RequestMapping("/itens")

public class ItemResource {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping
	public ResponseEntity<List<Item>> listar() {
		List<Item> items = itemService.listar();
		return ResponseEntity.ok(items);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> criar(@RequestBody Item item) {
		Item itemSalvo = itemService.salvar(item);
		return ResponseEntity.status(HttpStatus.CREATED).body(itemSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Item> buscarPeloCodigo(@PathVariable String id) {
		Optional<Item> item = itemService.buscarPorId(id);
		return item.isPresent() ? ResponseEntity.ok(item.get()) : ResponseEntity.notFound().build();
	}
	
}
