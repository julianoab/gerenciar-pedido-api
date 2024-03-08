package com.jab.gerenciapedidoapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jab.gerenciapedidoapi.model.Pedido;
import com.jab.gerenciapedidoapi.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> listar() {
		List<Pedido> listaPedido = pedidoService.listar();
		listaPedido.forEach(pedido -> System.err.println("pedido: " + pedido.toString()));
		return ResponseEntity.ok(pedidoService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
		Pedido pedidoSalvo = pedidoService.salvar(pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvo);
	}
}
