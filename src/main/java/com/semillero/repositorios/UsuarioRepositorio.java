package com.semillero.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.semillero.entidades.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	
	//@Query("SELECT c FROM usuario c WHERE c.apellido = :apellido")
	//public List<Usuario> listar(String apellido);
	
	@Query("SELECT c FROM Usuario c WHERE c.apellido LIKE :valor")
	public List<Usuario> listarPorApellidoQueEmpiecen(String valor);

}
