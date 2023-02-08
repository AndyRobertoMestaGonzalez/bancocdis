package com.cdisbanco.springboot.app.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cdisbanco.springboot.app.models.dao.ITarjetaDao;
import com.cdisbanco.springboot.app.models.entity.Tarjeta;

import jakarta.persistence.EntityManager;

public class TarjetaDaoImpl implements ITarjetaDao {
	private EntityManager em;
	@Override
	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Tarjeta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Tarjeta").getResultList();
	}

	@Override
	@Transactional
	public void Save(Tarjeta tarjeta) {
		// TODO Auto-generated method stub
		if(tarjeta.getId()!=null&& tarjeta.getId()>0) {
			em.merge(tarjeta);
			
		}else {
			em.persist(tarjeta);
		}
	}

	@Override
	@Transactional(readOnly = true)
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
