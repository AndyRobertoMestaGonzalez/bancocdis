package com.cdisbanco.springboot.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdisbanco.springboot.app.editors.ClientePropertyEditor;
import com.cdisbanco.springboot.app.editors.CuentaPropertyEditor;
import com.cdisbanco.springboot.app.models.dao.ClienteDaoImpl;
import com.cdisbanco.springboot.app.models.dao.IClienteDao;
import com.cdisbanco.springboot.app.models.dao.ICuentaDao;
import com.cdisbanco.springboot.app.models.entity.Cliente;
import com.cdisbanco.springboot.app.models.entity.Cuenta;
import com.cdisbanco.springboot.app.models.entity.Tarjeta;

import jakarta.persistence.Entity;


@Controller
@SessionAttributes("cuenta")
public class CuentaController {
	@Autowired
	private ICuentaDao cuentaDao;
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private ClientePropertyEditor clienteEditor;
	
	@org.springframework.web.bind.annotation.InitBinder
	public void InitBinder(WebDataBinder binder) {
		System.out.println("Pasa a una conversion");
		try {
			binder.registerCustomEditor(Cliente.class,"abc", clienteEditor);
		}catch(DataAccessException e) {
			System.out.println("Error");
		}
		
	}  
	
	@RequestMapping(value="/lista" ,method= RequestMethod.GET)
	public String cuentaLista(Model model) {
		model.addAttribute("titulo","Lista de cuentas");
		model.addAttribute("cuentas",cuentaDao.findAll());
		return "Lista";
	}
	
	@RequestMapping(value="/formcuenta")
	public String crear(Map<String,Object> model, Model modelList) {
		Cuenta cuenta = new Cuenta();
		List<Cliente> listaClientes = clienteDao.findAll();
		model.put("cuenta",cuenta);
		modelList.addAttribute("listaClientes", listaClientes);
		model.put("titulo","Llenar los datos de una cuenta");
		return "formcuenta";
	}
	@RequestMapping(value = "form-cuenta/{id}")
	public String editar(@PathVariable(value="id") Long id ,Map<String,Object> model ) {
		Cuenta cuenta = null;
		if(id >0) {
			cuenta = cuentaDao.findOne(id);
			
		}else{
			return "redirect:/lista";
		}
		model.put("cuenta",cuenta);
		model.put("titulo","Edite la cuenta");
		return "form cuenta";
	}
	@RequestMapping(value="/formcuenta",method = RequestMethod.POST)
	public String guardar(@Valid Cuenta cuenta, BindingResult result, Model model, SessionStatus status,RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Llene correctamente los campos");
			model.addAttribute("result",result.hasErrors());
			model.addAttribute("mensaje","Error al enviar los datos, por favor escriba correctamente");
			System.out.println("Errores"+result.getAllErrors());
			return "formcuenta";
		}else {
			model.addAttribute("result",false);
		}
		Cliente cliente2 = null;
		cliente2 = clienteDao.findOne(Long.parseLong("1"));
		cliente2.setNombre("Damian");
		model.addAttribute("titlo","Formulario de cuenta");
		model.addAttribute("mensage","Se envio la informacion correctamente");
		try {
			clienteDao.Save(cliente2);
			cuentaDao.Save(cuenta);
		}catch(DataAccessException e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje",e.getMessage());
			
		}
		status.setComplete();
		return "redirect:formcuenta";
	}
	@RequestMapping(value="/eliminarcuenta/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id >0) {
			cuentaDao.delete(id);
		}
		return "redirect:index";
	}
}
