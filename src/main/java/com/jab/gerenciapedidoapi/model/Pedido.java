package com.jab.gerenciapedidoapi.model;

import java.util.List;
import java.util.UUID;

public class Pedido {
	
	private UUID id;
	private Double valorTotal;
	private List<ItensPedido> intensPedido;
	
	public Pedido(Double valorTotal, List<ItensPedido> intensPedido) {
		this.valorTotal = valorTotal;
		this.intensPedido = intensPedido;
	}

	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItensPedido> getIntensPedido() {
		return intensPedido;
	}

	public void setIntensPedido(List<ItensPedido> intensPedido) {
		this.intensPedido = intensPedido;
	}
	
}
