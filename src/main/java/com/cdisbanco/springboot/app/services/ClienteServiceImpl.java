package com.cdisbanco.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdisbanco.springboot.app.models.entity.Cliente;
@Service
public class ClienteServiceImpl implements IClienteService {
	
	private List<Cliente> lista;
	public ClienteServiceImpl() {
		
	}
	@Override
	public Cliente getById(Long idCliente, List<Cliente> lista) {
		this.lista = lista;
		Cliente clienteResult = null;
		for( Cliente cliente : this.lista) {
			if(idCliente==cliente.getIdCliente()) {
				clienteResult = cliente;
				break;
			}
		}
		return clienteResult;
	}

}
