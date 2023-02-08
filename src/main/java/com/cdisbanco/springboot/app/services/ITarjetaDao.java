package com.cdisbanco.springboot.app.services;

import java.util.List;

import com.cdisbanco.springboot.app.models.entity.Tarjeta;

public interface ITarjetaDao {
	public List<Tarjeta> findAll();
	public void save(Tarjeta tarjeta);
	public Tarjeta findOne(Long id);
	public void delete(Long id);
}
