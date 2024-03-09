package com.jab.gerenciapedidoapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jab.gerenciapedidoapi.model.Item;
import com.jab.gerenciapedidoapi.model.ItemPedido;
import com.jab.gerenciapedidoapi.model.Pedido;
import com.jab.gerenciapedidoapi.model.TipoItem;
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
	
	public Optional<Pedido> buscarPorId(String id) {
		return pedidoRepository.findById(UUID.fromString(id));
	}

	public Pedido atualizar(Pedido pedido) {
		Optional<Pedido> pedidoPersistido = pedidoRepository.findById(pedido.getId());
		if (!pedidoPersistido.isPresent()) {
			throw new IllegalArgumentException("Item não encontrado");
		}
		BeanUtils.copyProperties(pedido, pedidoPersistido.get(), "id");
		return pedidoRepository.save(pedidoPersistido.get());
	}
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		Pedido pedidoSalvo = null;
		Double valorDesconto = 0.0;
		
		if (pedido != null) {
			
			for (ItemPedido itemPedido : pedido.getItens()) {
				 Item item = itemRepository.findById(itemPedido.getItem().getId()).get();
				 itemPedido.setItem(item);
				 itemPedido.setPedido(pedidoSalvo);
			}
		   
			if (deveAplicarDesconto(pedido)) {
				valorDesconto = getValorDesconto(pedido);
			}
			
			pedido.setValorTotal(pedido.getValorTotal() - valorDesconto);
			
			pedidoSalvo = pedidoRepository.save(pedido);
			
			itemPedidoRepository.saveAll(pedido.getItens());
		}
		
		return pedidoSalvo;
	}
	
	private boolean deveAplicarDesconto(Pedido pedido) {
		List<ItemPedido> itensPedido = pedido.getItens().stream()
				.filter(item -> item.getItem().getTipoItem().equals(TipoItem.PRODUTO))
				.collect(Collectors.toList());
		if (pedido.getPercentualDesconto() <= 0) {
			return false;
		} else if (itensPedido.isEmpty()) {
			return false;
		} 
		
		return true;
	}
	
	private Double getValorDesconto(Pedido pedido) {
		Double valorProdutos = 0.0;
		
		List<ItemPedido> itensPedidoProduto = pedido.getItens().stream()
				.filter(item -> item.getItem().getTipoItem().equals(TipoItem.PRODUTO))
				.collect(Collectors.toList());
		
		for (ItemPedido item : itensPedidoProduto) {
			valorProdutos += item.getItem().getPreco() * item.getQuantidade();
		}
		
		Double valorDesconto = valorProdutos * (pedido.getPercentualDesconto() / 100);
		
		return valorDesconto;
	}
	
}
