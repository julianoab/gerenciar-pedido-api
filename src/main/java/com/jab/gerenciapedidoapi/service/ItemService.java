package com.jab.gerenciapedidoapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jab.gerenciapedidoapi.model.Item;
import com.jab.gerenciapedidoapi.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository produtoServicoRepository;
	
	public List<Item> listar() {
		return produtoServicoRepository.findAll();
	}
	
	public Item salvar(Item produtoServico) {
		return produtoServicoRepository.save(produtoServico);
	}
	
	public Optional<Item> buscarPorId(String id) {
		Optional<Item> produto = produtoServicoRepository.findById(UUID.fromString(id));
		System.out.println("Nome produto: " + produto.get().getNome());
		return produtoServicoRepository.findById(UUID.fromString(id));
	}
	
	public void excluir(String id) {
		produtoServicoRepository.deleteById(UUID.fromString(id));
	}
}
