package com.personalblog.blogisa.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.personalblog.blogisa.model.Usuario;
import com.personalblog.blogisa.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImplements implements UserDetailsService{
	
	@Autowired 
	private UsuarioRepository usuarioRepositorio;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
	        Optional<Usuario> user = usuarioRepositorio.findByEmailUsuario(userEmail);
	        user.orElseThrow(() -> new UsernameNotFoundException(userEmail + " not found."));

	        return user.map(UserDetailsImplements::new).get();
	    }
	}