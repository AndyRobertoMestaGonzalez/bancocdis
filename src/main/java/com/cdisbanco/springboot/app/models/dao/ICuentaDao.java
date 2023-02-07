package com.cdisbanco.springboot.app.models.dao;

import java.util.List;

import com.cdisbanco.springboot.app.models.entity.Cuenta;
import com.cdisbanco.springboot.app.models.entity.Tarjeta;
public interface ICuentaDao {
	public List<Cuenta> findAll();
	public void Save(Cuenta cuenta);
	public Cuenta findOne(Long id);
	public void delete(Long id);
}
