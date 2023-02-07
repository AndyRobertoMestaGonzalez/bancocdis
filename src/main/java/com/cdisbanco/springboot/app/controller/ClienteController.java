package com.cdisbanco.springboot.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdisbanco.springboot.app.models.dao.IClienteDao;
import com.cdisbanco.springboot.app.models.dao.ICuentaDao;
import com.cdisbanco.springboot.app.models.entity.Cliente;
import com.cdisbanco.springboot.app.models.entity.Cuenta;

@Controller

public class ClienteController {
	@Autowired
	private IClienteDao clienteDao;
	@RequestMapping(value="/clientes-lista" ,method= RequestMethod.GET)
	public String clienteLista(Model model) {
		model.addAttribute("titulo","Lista de clientes");
		model.addAttribute("clientes",clienteDao.findAll());
		return "clientes-lista";
	}
	
	@RequestMapping(value="/formClientes",method= RequestMethod.GET)
	public String crear(Map<String,Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente",cliente);
		model.put("titulo","Nuevo cliente, llene los datos");
		return "formClientes";
	}
	@RequestMapping(value="/formClientes",method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status,RedirectAttributes flash) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Llene correctamente los campos");
			model.addAttribute("result",result.hasErrors());
			model.addAttribute("mensaje","Error al enviar los datos, por favor escriba correctamente");
			
			return "formClientes";
		}else {
			model.addAttribute("result",false);
		}
		model.addAttribute("titlo","Formulario de cliente");
		model.addAttribute("mensage","Se envio la informacion correctamente");
		try {
			clienteDao.Save(cliente);
		}catch(DataAccessException e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje",e.getMessage());
			
		}
		status.setComplete();
		return "redirect:formClientes";
	}
}
