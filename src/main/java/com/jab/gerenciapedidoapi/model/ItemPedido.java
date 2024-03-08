package com.jab.gerenciapedidoapi.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
//	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	
	private Integer quantidade;

	public ItemPedido() {}
	

	public ItemPedido(UUID id, Item item, Pedido pedido, Integer quantidade) {
		this.id = id;
		this.item = item;
		this.pedido = pedido;
		this.quantidade = quantidade;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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


	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", item=" + item + ", pedido=" + pedido + ", quantidade=" + quantidade + "]";
	}
}
