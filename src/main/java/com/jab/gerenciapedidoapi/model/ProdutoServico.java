package com.jab.gerenciapedidoapi.model;

import java.util.UUID;

public class ProdutoServico {
	
	private UUID id;
	private String nome;
	private Double preco;
	private Tipo tipo;
	
	public ProdutoServico() {};
	
	public ProdutoServico(UUID id, String nome, Double preco, Tipo tipo) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}
