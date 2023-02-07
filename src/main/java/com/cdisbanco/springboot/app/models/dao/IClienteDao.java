package com.cdisbanco.springboot.app.models.dao;

import java.util.List;

import com.cdisbanco.springboot.app.models.entity.Cliente;
import com.cdisbanco.springboot.app.models.entity.Cuenta;

public interface IClienteDao {
	public List<Cliente> findAll();
	public void Save(Cliente cliente);
	public Cliente findOne(Long id);
	public void delete(Long id);
}
