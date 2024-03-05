package com.jab.gerenciapedidoapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<Pedido> listar() {
		Pedido pedido = pedidoService.criar();
		return ResponseEntity.ok(pedido);
	}
}
