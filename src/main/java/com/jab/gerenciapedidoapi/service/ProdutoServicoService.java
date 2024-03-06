package com.jab.gerenciapedidoapi.service;

import java.util.List;
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
	
}
