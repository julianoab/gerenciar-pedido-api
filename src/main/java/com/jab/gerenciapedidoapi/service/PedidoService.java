package com.jab.gerenciapedidoapi.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jab.gerenciapedidoapi.model.ItensPedido;
import com.jab.gerenciapedidoapi.model.Pedido;
import com.jab.gerenciapedidoapi.model.ProdutoServico;
import com.jab.gerenciapedidoapi.model.Tipo;

@Service
public class PedidoService {
	
	public Pedido criar() {
		ProdutoServico produtoServico = new ProdutoServico(UUID.randomUUID(), "Mouse", 40.00, Tipo.PRODUTO);
		ItensPedido itensPedido = new ItensPedido(UUID.randomUUID(), produtoServico, 10);
		return new Pedido(80.00, Arrays.asList(itensPedido));
	}
	
}
