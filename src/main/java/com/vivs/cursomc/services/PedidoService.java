package com.vivs.cursomc.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vivs.cursomc.domain.ItemPedido;
import com.vivs.cursomc.domain.PagamentoComBoleto;
import com.vivs.cursomc.domain.Pedido;
import com.vivs.cursomc.domain.enums.EstadoPagamento;
import com.vivs.cursomc.repositories.ItemPedidoRepository;
import com.vivs.cursomc.repositories.PagamentoRepository;
import com.vivs.cursomc.repositories.PedidoRepository;
import com.vivs.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {
	
	//dependencia automaticamente instanciada pelo spring 
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(LocalDateTime.now());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		/*Pedido pedido = obj;
		List<ItemPedido> listaItemAtualizado = 
				obj.getItens().stream()
			             .peek(ip -> ip.setDesconto(0.0))
			             .peek(ip -> ip.setProduto(produtoService.find(ip.getProduto().getId())))
			             .peek(ip -> ip.setPreco(ip.getProduto().getPreco()))
			             .peek(ip -> ip.setPedido(pedido))
			             .collect (Collectors.toList());*/
		itemPedidoRepository.saveAll(obj.getItens());
		//itemPedidoRepository.saveAll(listaItemAtualizado);
		
		System.out.println(obj);
		//emailService.sendOrderConfirmationEmail(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

}
