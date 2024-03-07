package com.jab.gerenciapedidoapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jab.gerenciapedidoapi.model.ProdutoServico;
import com.jab.gerenciapedidoapi.repository.ProdutoServicoRepository;

@Service
public class ProdutoServicoService {
	
	@Autowired
	private ProdutoServicoRepository produtoServicoRepository;
	
	public List<ProdutoServico> listar() {
		return produtoServicoRepository.findAll();
	}
	
	public ProdutoServico salvar(ProdutoServico produtoServico) {
		return produtoServicoRepository.save(produtoServico);
	}
	
	public Optional<ProdutoServico> buscarPorId(String id) {
		Optional<ProdutoServico> produto = produtoServicoRepository.findById(UUID.fromString(id));
		System.out.println("Nome produto: " + produto.get().getNome());
		return produtoServicoRepository.findById(UUID.fromString(id));
	}
	
	public void excluir(String id) {
		produtoServicoRepository.deleteById(UUID.fromString(id));
	}
}
