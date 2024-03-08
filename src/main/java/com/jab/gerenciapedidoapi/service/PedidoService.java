package com.jab.gerenciapedidoapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jab.gerenciapedidoapi.model.Item;
import com.jab.gerenciapedidoapi.model.ItemPedido;
import com.jab.gerenciapedidoapi.model.Pedido;
import com.jab.gerenciapedidoapi.repository.ItemPedidoRepository;
import com.jab.gerenciapedidoapi.repository.ItemRepository;
import com.jab.gerenciapedidoapi.repository.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	public List<Pedido> listar() {
		return pedidoRepository.findAll();
	}
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		Pedido pedidoSalvo = null;
		if (pedido != null) {
		   
			pedidoSalvo = pedidoRepository.save(pedido);
		
			for (ItemPedido itemPedido : pedido.getItens()) {
				 Item item = itemRepository.findById(itemPedido.getItem().getId()).get();
				 itemPedido.setItem(item);
				 itemPedido.setPedido(pedidoSalvo);
			}
			
			itemPedidoRepository.saveAll(pedido.getItens());
		}
		return pedidoSalvo;
	}
}
