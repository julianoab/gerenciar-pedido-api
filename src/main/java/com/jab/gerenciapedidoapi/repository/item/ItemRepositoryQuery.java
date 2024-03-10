package com.jab.gerenciapedidoapi.repository.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jab.gerenciapedidoapi.model.Item;
import com.jab.gerenciapedidoapi.repository.filter.ItemFilter;


public interface ItemRepositoryQuery {
	
	public Page<Item> filtrar(ItemFilter itemFilter, Pageable pageable);

}
