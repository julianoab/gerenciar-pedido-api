package com.jab.gerenciapedidoapi.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String nome;
	
	private Double preco;
	
	@Enumerated(EnumType.STRING)
	private TipoItem tipoItem;
	
	//@JsonManagedReference
	@OneToMany(mappedBy = "item")
	private Set<ItemPedido> itemPedido = new HashSet<>();
	
	public Item() {};
	
	public Item(UUID id, String nome, Double preco, TipoItem tipoItem) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.tipoItem = tipoItem;
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

	public TipoItem getTipo() {
		return tipoItem;
	}

	public void setTipo(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public Set<ItemPedido> getItemPedido() {
		return itemPedido;
	}

	public void setItemPedido(Set<ItemPedido> itemPedido) {
		this.itemPedido = itemPedido;
	}

	/*
	 * @Override public int hashCode() { return Objects.hash(id); }
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return true;
	 * if (obj == null) return false; if (getClass() != obj.getClass()) return
	 * false; Item other = (Item) obj; return Objects.equals(id, other.id); }
	 */

	/*
	 * @Override public String toString() { return "Item [id=" + id + ", nome=" +
	 * nome + ", preco=" + preco + ", tipoItem=" + tipoItem + ", itemPedido=" +
	 * itemPedido + "]"; }
	 */
	
}
