package com.semillero.servicios;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semillero.entidades.Usuario;
import com.semillero.repositorios.UsuarioRepositorio;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Transactional
	public void guardar(String nombre, String apellido, Long documento, String descripcion) throws Exception {
		
		if(nombre==null || nombre.isEmpty()) {
			throw new Exception("El nombre del usuario no puede ser nulo.");
		}
		if(apellido==null || apellido.isEmpty()) {
			throw new Exception("El apellido del usuario no puede ser nulo.");
		}
		if(documento == null || documento.intValue()<=0) {
			throw new Exception("El documento del usuario es incorrecto.");
		}
		
		Usuario usuario = new Usuario();
		usuario.setApellido(apellido);
		usuario.setNombre(nombre);
		usuario.setDocumento(documento);
		usuario.setDescripcion(descripcion);
		
		usuarioRepositorio.save(usuario);
		
	}
	
	@Transactional
	public void modificar(Long documento, String descripcion) throws Exception{
		Optional<Usuario> respuesta = usuarioRepositorio.findById(documento);
		if(respuesta.isPresent()){
			Usuario usuario = respuesta.get();
			usuario.setDescripcion(descripcion);
			usuarioRepositorio.save(usuario);
		}else {
			throw new Exception("No se encontró un usuario con ese documento");
		}
		
		
	}

	@Transactional
	public void eliminar(Long documento) throws Exception{
		// TODO Auto-generated method stub
		Optional<Usuario> respuesta = usuarioRepositorio.findById(documento);
		if(respuesta.isPresent()){
			Usuario usuario = respuesta.get();
			usuarioRepositorio.delete(usuario);
		}else {
			throw new Exception("No se encontró un usuario con ese documento");
		}
	}

	public List<Usuario> buscarPorApellido(String apellido) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.listarPorApellidoQueEmpiecen(apellido + "%");
		//si % al final busca los que empiezan con la variable
		//si % al principio busca los que terminan con la variable
		//si % al principio y al final busca los que contengan la variable
	}

}
