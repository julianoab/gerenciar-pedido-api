package com.jab.gerenciapedidoapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jab.gerenciapedidoapi.model.Item;
import com.jab.gerenciapedidoapi.repository.item.ItemRepositoryQuery;


@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>, ItemRepositoryQuery {
	
}
