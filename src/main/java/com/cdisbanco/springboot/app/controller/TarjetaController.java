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

import com.cdisbanco.springboot.app.editors.CuentaPropertyEditor;
import com.cdisbanco.springboot.app.models.dao.ICuentaDao;
import com.cdisbanco.springboot.app.models.dao.ITarjetaDao;
import com.cdisbanco.springboot.app.models.entity.Tarjeta;

import com.cdisbanco.springboot.app.models.entity.Cuenta;

@Controller
@SessionAttributes("tarjeta")
public class TarjetaController {
	@Autowired
	private ITarjetaDao tarjetaDao;
	
	@Autowired
	private ICuentaDao cuentaDao;
	
	@Autowired
	private CuentaPropertyEditor cuentaEditor;
	 
	@org.springframework.web.bind.annotation.InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cuenta.class,"cuenta", cuentaEditor);
	}

	@RequestMapping(value="/tarjetas-lista" ,method= RequestMethod.GET)
	public String tarjetaLista(Model model) {
		model.addAttribute("titulo","Lista de tarjetas");
		model.addAttribute("tarjetas",tarjetaDao.findAll());
		return "tarjetas-lista";
	}
	@RequestMapping(value="/formtarjeta",method=RequestMethod.GET)
	public String crear(Map<String,Object> model, Model modelList) {
		Tarjeta tarjeta = new Tarjeta();
		List<Cuenta> listaCuentas = cuentaDao.findAll();
		model.put("tarjeta",tarjeta);
		modelList.addAttribute("listaCuentas", listaCuentas);
		model.put("titulo","Llenar los datos de una tarjeta");
		return "formtarjeta";
	}
	@RequestMapping(value ="/formtarjeta/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model) {
		Tarjeta tarjeta = null;
		if(id !=null && id > 0) {
			tarjeta = tarjetaDao.findOne(id);
			
		}else {
			return "redirect:/index";
		}
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Editar tarjeta");
		return "formtarjeta";
	}
	@RequestMapping(value="/formtarjeta",method = RequestMethod.POST)
	public String guardar(@Valid Tarjeta tarjeta, BindingResult result, Model model, SessionStatus status,RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Llene correctamente los campos");
			model.addAttribute("result",result.hasErrors());
			model.addAttribute("mensaje","Error al enviar los datos, por favor escriba correctamente ");
			return "formtarjeta";
		}else {
			model.addAttribute("result",false);
		}
		
		model.addAttribute("titulo","Formulario de tarjta");
		model.addAttribute("mensaje","Se envio la informacion correctamente");
		try {
			tarjetaDao.Save(tarjeta);
		}catch(DataAccessException e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje",e.getMessage());
		}
		status.setComplete();
		return "redirect:formtarjeta";
	}
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if(id !=null && id > 0) {
			tarjetaDao.delete(id);
			
		}
		return "redirect:/tarjetas-lista";
	}
	
}
