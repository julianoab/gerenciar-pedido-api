package com.jab.gerenciapedidoapi.model;

import java.util.UUID;

public class ItensPedido {
	
	private UUID id;
	private ProdutoServico produtoServico;
	private Integer quantidade;
	
	public ItensPedido(UUID id, ProdutoServico produtoServico, Integer quantidade) {
		this.id = id;
		this.produtoServico = produtoServico;
		this.quantidade = quantidade;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public ProdutoServico getProdutoServico() {
		return produtoServico;
	}

	public void setProdutoServico(ProdutoServico produtoServico) {
		this.produtoServico = produtoServico;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
}
