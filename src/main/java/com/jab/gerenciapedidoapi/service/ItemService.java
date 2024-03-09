package com.jab.gerenciapedidoapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jab.gerenciapedidoapi.model.Item;
import com.jab.gerenciapedidoapi.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> listar() {
		return itemRepository.findAll();
	}
	
	public Item salvar(Item produtoServico) {
		return itemRepository.save(produtoServico);
	}
	
	public Optional<Item> buscarPorId(String id) {
		return itemRepository.findById(UUID.fromString(id));
	}
	
	public void excluir(String id) {
		itemRepository.deleteById(UUID.fromString(id));
	}

	public Item atualizar(Item item) {
		Optional<Item> itemPersitido = itemRepository.findById(item.getId());
		if (!itemPersitido.isPresent()) {
			throw new IllegalArgumentException("Item não encontrado");
		}
		BeanUtils.copyProperties(item, itemPersitido.get(), "id");
		return itemRepository.save(itemPersitido.get());
	}
	
}
