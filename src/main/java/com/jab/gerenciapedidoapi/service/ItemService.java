package com.jab.gerenciapedidoapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jab.gerenciapedidoapi.model.Item;
import com.jab.gerenciapedidoapi.model.SituacaoItem;
import com.jab.gerenciapedidoapi.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
		
	
    public List<Item> listar() { 
    	return itemRepository.findAll();
    }
		
	public Item salvar(Item item) {
		item.setSituacaoItem(SituacaoItem.ATIVO);
		return itemRepository.save(item);
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

	/*
	 * public Page<Item> findPage(Integer pagina, Integer linhasPorPagina, String
	 * orderBy, String direction) { PageRequest pageRequest = PageRequest.of(pagina,
	 * linhasPorPagina, Direction.valueOf(direction), orderBy); return
	 * itemRepository.findPage(pageRequest); }
	 */
	
}
