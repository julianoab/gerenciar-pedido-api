package com.jab.gerenciapedidoapi.model;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItemPedidoId {
	
	@Column(name = "pedido_id")
	private UUID pedidoId;
	
	@Column(name = "produto_servico_id")
	private UUID produtoServicoId;
	

	public ItemPedidoId() {}

	public ItemPedidoId(UUID pedidoId, UUID produtoID) {
		this.pedidoId = pedidoId;
		this.produtoServicoId = produtoID;
	}

	public UUID getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(UUID pedidoId) {
		this.pedidoId = pedidoId;
	}

	public UUID getProdutoServicoId() {
		return produtoServicoId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedidoId, produtoServicoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoId other = (ItemPedidoId) obj;
		return Objects.equals(pedidoId, other.pedidoId) && Objects.equals(produtoServicoId, other.produtoServicoId);
	}
	
}
