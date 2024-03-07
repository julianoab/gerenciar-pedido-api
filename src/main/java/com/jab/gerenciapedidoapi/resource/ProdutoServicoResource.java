package com.jab.gerenciapedidoapi.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping
	public ResponseEntity<ProdutoServico> criar(@RequestBody ProdutoServico produtoServico) {
		ProdutoServico produtoServicoSalvo = produtoServicoService.salvar(produtoServico);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoServicoSalvo);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoServico> buscarPeloCodigo(@PathVariable String id) {
		Optional<ProdutoServico> produtoServico = produtoServicoService.buscarPorId(id);
		return produtoServico.isPresent() ? ResponseEntity.ok(produtoServico.get()) : ResponseEntity.notFound().build();
	}
	
}
