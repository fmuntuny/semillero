package com.semillero.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.semillero.entidades.Usuario;
import com.semillero.servicios.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/registro")
	public String registro() {
		return "registro.html";
	}
	
	@GetMapping("/buscar")
	public String buscar(ModelMap modelo, @RequestParam(required=false) String apellido) {
		
		List<Usuario> usuarios = new ArrayList<>();
		if(apellido != null) {
			usuarios = usuarioService.buscarPorApellido(apellido);
		}
		
		modelo.put("usuarios", usuarios);
		return "usuarios.html";
	}
	
	@PostMapping("/guardar")
	public String guardar(ModelMap modelo, @RequestParam String nombre,
			@RequestParam String apellido,
			@RequestParam Long documento,
			@RequestParam String descripcion) {
		System.out.println("Nombre: " + nombre);
		System.out.println("Apellido: " + apellido);
		System.out.println("Documento: " + documento);
		System.out.println("Bio: " + descripcion );
		
		try {
			usuarioService.guardar( nombre, apellido, documento, descripcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha ocurrido el error: " + e.getMessage());
			modelo.put("error", e.getMessage());
			return "error.html";
		}
		
		return "gracias.html";
	}
	
	@PostMapping("/modificar")
	public String modificar(ModelMap modelo,
			@RequestParam Long documento,
			@RequestParam String descripcion) {
		System.out.println("Documento: " + documento);
		System.out.println("Bio: " + descripcion );
		
		try {
			usuarioService.modificar( documento, descripcion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha ocurrido el error: " + e.getMessage());
			modelo.put("error", e.getMessage());
			return "error.html";
		}
		
		return "gracias.html";
	}
	
	@PostMapping("/eliminar")
	public String eliminar(ModelMap modelo,
			@RequestParam Long documento) {
		System.out.println("Documento: " + documento);
		
		try {
			usuarioService.eliminar( documento);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha ocurrido el error: " + e.getMessage());
			modelo.put("error", e.getMessage());
			return "error.html";
		}
		
		return "gracias.html";
	}
}
