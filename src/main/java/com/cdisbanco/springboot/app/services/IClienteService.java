package com.cdisbanco.springboot.app.services;

import java.util.List;

import com.cdisbanco.springboot.app.models.entity.Cliente;
import com.cdisbanco.springboot.app.models.entity.Cuenta;

public interface IClienteService {
	public Cliente getById(Long idCliente, List<Cliente> lista);
}
