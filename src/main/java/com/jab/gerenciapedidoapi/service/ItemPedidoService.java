package com.jab.gerenciapedidoapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jab.gerenciapedidoapi.model.ItemPedido;
import com.jab.gerenciapedidoapi.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> listar() {
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido salvar(ItemPedido ItemPedido) {
		return itemPedidoRepository.save(ItemPedido);
	}
	
	public Optional<ItemPedido> buscarPorId(String id) {
		return itemPedidoRepository.findById(UUID.fromString(id));
	}
	
	public void excluir(String id) {
		itemPedidoRepository.deleteById(UUID.fromString(id));
	}
	
	public ItemPedido atualizar(ItemPedido itemPedido) {
		Optional<ItemPedido> itemPedidoPersitido = itemPedidoRepository.findById(itemPedido.getId());
		if (!itemPedidoPersitido.isPresent()) {
			throw new IllegalArgumentException("ItemPedido não encontrado");
		}
		BeanUtils.copyProperties(itemPedido, itemPedidoPersitido.get(), "id");
		return itemPedidoRepository.save(itemPedidoPersitido.get());
	}
}
