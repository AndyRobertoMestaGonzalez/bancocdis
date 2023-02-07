package com.cdisbanco.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisbanco.springboot.app.models.entity.Tarjeta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@Repository
public class TarjetaDaoImpl implements ITarjetaDao {

	@PersistenceContext 
	/*Consulta el archivo de application.properties para entrar a la basa de datos mas especificamente a ya esta conectado pero con este las clases podran hacer modificaciones*/
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Tarjeta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Tarjeta").getResultList();
	}


	@Transactional
	@Override
	public void Save(Tarjeta tarjeta) {
		if(tarjeta.getId()!=null && tarjeta.getId()>0) {
			em.merge(tarjeta);
			
		}else {
			em.persist(tarjeta);
		}
		
	}


	@Override
	@Transactional
	public Tarjeta findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Tarjeta.class,id);
	}


	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
		
	}

}

