package com.cdisbanco.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisbanco.springboot.app.models.entity.Cliente;
import com.cdisbanco.springboot.app.models.entity.Cuenta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ClienteDaoImpl  implements IClienteDao{
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void Save(Cliente cliente) {
		// TODO Auto-generated method stub
		if(cliente.getIdCliente()!=null && cliente.getIdCliente()>0) {
			em.merge(cliente);
			
		}else {
			em.persist(cliente);
		}
		
	}

	@Override
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}
	
	
	
}
