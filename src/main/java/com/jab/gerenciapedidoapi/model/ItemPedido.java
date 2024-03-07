package com.jab.gerenciapedidoapi.model;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	
	@EmbeddedId
	private ItemPedidoId id;
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;*/
	
	@ManyToOne
	@JoinColumn(name = "produto_servico_id", insertable=false, updatable=false)
	private ProdutoServico produtoServico;
	
	@ManyToOne
	@JoinColumn(name = "pedido_id", insertable=false, updatable=false)
	private Pedido pedido;
	
	private Integer quantidade;

	public ItemPedido() {
	}

	public ItemPedido(ProdutoServico produtoServico, Pedido pedido, Integer quantidade) {
		this.produtoServico = produtoServico;
		this.pedido = pedido;
		this.quantidade = quantidade;
	}

//	public UUID getId() {
//		return id;
//	}
//	
//	public void setId(UUID id) {
//		this.id = id;
//	}
	
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
	
}
