package com.cdisbanco.springboot.app.services;

import java.util.List;

import com.cdisbanco.springboot.app.models.entity.Cuenta;

public interface ICuentaService {
	public Cuenta getById(Long id, List<Cuenta> lista);
}
