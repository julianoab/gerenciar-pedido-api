package com.jab.gerenciapedidoapi.service;

import java.util.ArrayList;
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
import com.jab.gerenciapedidoapi.model.SituacaoItem;
import com.jab.gerenciapedidoapi.model.SituacaoPedido;
import com.jab.gerenciapedidoapi.model.TipoItem;
import com.jab.gerenciapedidoapi.repository.ItemPedidoRepository;
import com.jab.gerenciapedidoapi.repository.ItemRepository;
import com.jab.gerenciapedidoapi.repository.PedidoRepository;
import com.jab.gerenciapedidoapi.service.exception.BusinessException;
import com.jab.gerenciapedidoapi.service.exception.RegistroNaoEncontradoException;


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
		Optional<Pedido> pedido = pedidoRepository.findById(UUID.fromString(id));
		if (!pedido.isPresent()) {
			throw new RegistroNaoEncontradoException("Pedido não encontrado");
		}
		return pedido;
	}
	
	@Transactional
	public Pedido atualizar(Pedido pedido) {
		Optional<Pedido> pedidoPersistido = buscarPorId(pedido.getId().toString());
		
		if (!podeRealizarNovoDesconto(pedido)) {
			throw new BusinessException("Pedido fechado não pode receber novo desconto");
		} 
		
		List<ItemPedido> itensAremover = this.itemParaSeremDeletados(pedido, pedidoPersistido.get());
		
		if (itensAremover.size() > 0 && !itensAremover.isEmpty()) {
			itemPedidoRepository.deleteAll(itensAremover);
		}
		
		BeanUtils.copyProperties(pedido, pedidoPersistido.get(), "id");
		
		return salvar(pedidoPersistido.get());
	}
	
	
	public boolean podeRealizarNovoDesconto(Pedido pedido) {
		return pedido.getSituacaoPedido().equals(SituacaoPedido.ABERTO) 
				&& pedido.getPercentualDesconto() > 0;
	}
	
	public void deletarItemPedido(List<ItemPedido> itensPedido) {
		itemPedidoRepository.deleteById(itensPedido.get(0).getId());
	}
	
	private List<ItemPedido> itemParaSeremDeletados(Pedido pedidoAlterado, Pedido pedidoPersistido) {
		List<ItemPedido> idItensASeremExcluidos = new ArrayList<>();
		
		for (ItemPedido itemPersistido : pedidoPersistido.getItens()) {
			if (!pedidoAlterado.getItens().contains(itemPersistido)) {
				idItensASeremExcluidos.add(itemPersistido);
			}
		}
		return idItensASeremExcluidos;
	}
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		Double valorDesconto = 0.0;
		
		if (pedido.getId() == null) {
			pedido.setSituacaoPedido(SituacaoPedido.ABERTO);
		}
		
		if (pedido != null) {
			
			for (ItemPedido itemPedido : pedido.getItens()) {
				 Item item = itemRepository.findById(itemPedido.getItem().getId()).get();
					 itemPedido.setItem(item);
					 itemPedido.setPedido(pedido); 
			}
		   
			removeItensDesativadosPedido(pedido);
			
			if (deveAplicarDesconto(pedido)) {
				valorDesconto = getValorDesconto(pedido);
				pedido.setValorDesconto(valorDesconto);
			} 
			
			if (pedido.getSituacaoPedido().equals(SituacaoPedido.FECHADO)) {
				valorDesconto = pedido.getValorDesconto();
			}  

			pedido.setValorTotal(calculaValorTotalItens(pedido.getItens()) - valorDesconto);
			
			pedido = pedidoRepository.save(pedido);
			
			itemPedidoRepository.saveAll(pedido.getItens());
		}
		
		return pedido;
	}
	
	public void removeItensDesativadosPedido(Pedido pedido) {
		pedido.getItens().removeIf(itemPedido -> itemPedido.getItem().getSituacaoItem().equals(SituacaoItem.DESATIVADO));
	}
	
	private boolean deveAplicarDesconto(Pedido pedido) {
		
		if (pedido.getPercentualDesconto() <= 0) {
			return false;
		} 
		if (getItensTipoProduto(pedido).isEmpty() || pedido.getSituacaoPedido().equals(SituacaoPedido.FECHADO)) {
			return false;
		} 
		
		return true;
	}
	
	private Double getValorDesconto(Pedido pedido) {
		
		Double valorDesconto = 0.0;
		
		List<ItemPedido> itensPedidoTipoProduto = getItensTipoProduto(pedido);
		
		if (!itensPedidoTipoProduto.isEmpty() && itensPedidoTipoProduto.size() > 0) {
			Double valorProdutos = 0.0;
			for (ItemPedido item : itensPedidoTipoProduto) {
				valorProdutos += item.getItem().getPreco() * item.getQuantidade();
			}
			valorDesconto =  pedido.getPercentualDesconto() * ( valorProdutos / 100);
		}
		
		return valorDesconto;
	}
	
	private Double calculaValorTotalItens(List<ItemPedido> itemPedido) {
		Double valorTotal = 0.0;
		for (ItemPedido item : itemPedido) {
			valorTotal += item.getItem().getPreco() * item.getQuantidade();
		}
		return valorTotal;
	}
	
	private List<ItemPedido> getItensTipoProduto(Pedido pedido) {
		return pedido.getItens().stream()
				.filter(item -> item.getItem().getTipoItem().equals(TipoItem.PRODUTO))
				.collect(Collectors.toList());
	}
	
}
