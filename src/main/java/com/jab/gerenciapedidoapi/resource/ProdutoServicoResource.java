package com.jab.gerenciapedidoapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jab.gerenciapedidoapi.model.ProdutoServico;
import com.jab.gerenciapedidoapi.service.ProdutoServicoService;

@RestController
@RequestMapping("/produto-servico")
public class ProdutoServicoResource {
	
	@Autowired
	private ProdutoServicoService produtoServicoService;
	
	@GetMapping
	public ResponseEntity<List<ProdutoServico>> listar() {
		List<ProdutoServico> produtoServicos = produtoServicoService.listar();
		return ResponseEntity.ok(produtoServicos);
	}
}
