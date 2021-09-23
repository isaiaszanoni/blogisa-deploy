package com.personalblog.blogisa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personalblog.blogisa.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByNomeUsuarioContainingIgnoreCase (String nomeUsuario);
	
	Optional<Usuario> findByEmailUsuario(String email);

}
