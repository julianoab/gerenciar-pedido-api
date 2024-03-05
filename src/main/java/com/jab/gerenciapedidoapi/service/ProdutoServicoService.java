package com.jab.gerenciapedidoapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jab.gerenciapedidoapi.model.ProdutoServico;
import com.jab.gerenciapedidoapi.model.Tipo;

@Service
public class ProdutoServicoService {
	
	public List<ProdutoServico> listar() {
		ProdutoServico produtoServico = new ProdutoServico();
		produtoServico.setId(UUID.randomUUID());
		produtoServico.setNome("Notebook");
		produtoServico.setPreco(350.70);
		produtoServico.setTipo(Tipo.PRODUTO);
		
		return Arrays.asList(produtoServico);
	}
}
