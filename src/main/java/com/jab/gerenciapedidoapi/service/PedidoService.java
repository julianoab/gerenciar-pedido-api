package com.jab.gerenciapedidoapi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jab.gerenciapedidoapi.model.ItemPedido;
import com.jab.gerenciapedidoapi.model.Pedido;
import com.jab.gerenciapedidoapi.model.ProdutoServico;
import com.jab.gerenciapedidoapi.repository.ItemPedidoRepository;
import com.jab.gerenciapedidoapi.repository.PedidoRepository;
import com.jab.gerenciapedidoapi.repository.ProdutoServicoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ProdutoServicoRepository produtoServicoRepository;
	
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		
		for (ItemPedido itemPedido : pedido.getItemPedido()) {
			
			 ProdutoServico produtoServico = produtoServicoRepository.findById(itemPedido.getProdutoServico().getId()).get();
			 itemPedido.setProdutoServico(produtoServico);
			 itemPedido.setPedido(pedido);
			 itemPedidoRepository.save(itemPedido);
			 
		}
		return pedidoRepository.save(pedido);
	}
}
