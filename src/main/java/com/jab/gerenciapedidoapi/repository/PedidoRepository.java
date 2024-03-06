package com.jab.gerenciapedidoapi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jab.gerenciapedidoapi.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

}
