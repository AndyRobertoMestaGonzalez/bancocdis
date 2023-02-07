package com.cdisbanco.springboot.app.editors;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdisbanco.springboot.app.models.dao.IClienteDao;
import com.cdisbanco.springboot.app.models.dao.ICuentaDao;
import com.cdisbanco.springboot.app.services.IClienteService;
import com.cdisbanco.springboot.app.services.ICuentaService;

@Component
public class ClientePropertyEditor extends PropertyEditorSupport {
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IClienteDao clienteDao;
	@Override
	public void setAsText(String idStr) throws IllegalArgumentException{
		try {
			System.out.println("ID del cliente:"+idStr);
			Long idCliente = Long.parseLong(idStr);
			this.setValue(clienteService.getById(idCliente, clienteDao.findAll()));
		}catch(Exception e) {
			System.out.println("Hubo un error al asignar el objeto cliente a cuenta");
		}
	}
	
	
}
