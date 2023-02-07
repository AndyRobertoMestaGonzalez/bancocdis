package com.cdisbanco.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisbanco.springboot.app.models.entity.Cuenta;
import com.cdisbanco.springboot.app.models.entity.Tarjeta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CuentaDaoImpl implements ICuentaDao {
	@PersistenceContext 
	/*Consulta el archivo de application.properties para entrar a la basa de datos mas especificamente a ya esta conectado pero con este las clases podran hacer modificaciones*/
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cuenta").getResultList();
	}

	@Override
	@Transactional
	public void Save(Cuenta cuenta) {
		if(cuenta.getId()!=null && cuenta.getId()>0) {
			em.merge(cuenta);
			
		}else {
			em.persist(cuenta);
		}
		
	}

	@Override
	public Cuenta findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Cuenta.class, id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}
	
}
