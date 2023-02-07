package com.cdisbanco.springboot.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cdisbanco.springboot.app.models.entity.Cuenta;

@Service
public class CuentaServiceImpl implements ICuentaService {
	
	private List<Cuenta> lista;
	
	public CuentaServiceImpl() {
		
	}
	@Override
	public Cuenta getById(Long id, List<Cuenta> lista) {
		// TODO Auto-generated method stub
		this.lista = lista;
		Cuenta cuentaResult = null;
		for (Cuenta cuenta : this.lista) {
			if(id== cuenta.getId()) {
				System.out.println("Existe la cuenta");
				cuentaResult = cuenta;
				break;
			}
		}
		return cuentaResult;
	}
	
}
