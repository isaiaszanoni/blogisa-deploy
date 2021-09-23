package com.personalblog.blogisa.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.personalblog.blogisa.model.Usuario;
import com.personalblog.blogisa.model.UsuarioLogin;
import com.personalblog.blogisa.repository.UsuarioRepository;

@Service
public class UserService {

	@Autowired
	private UsuarioRepository repository;

	public Optional<Object> cadastrarUsuario(Usuario novoUsuario) {
		return repository.findByEmailUsuario(novoUsuario.getEmailUsuario()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String result = encoder.encode(novoUsuario.getSenhaUsuario());
			novoUsuario.setSenhaUsuario(result);
			return Optional.ofNullable(repository.save(novoUsuario));
		});
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByEmailUsuario(user.get().getEmailUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenhaUsuario(), usuario.get().getSenhaUsuario())) {

				String auth = user.get().getEmailUsuario() + ":" + user.get().getSenhaUsuario();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);
				user.get().setEmailUsuario(usuario.get().getEmailUsuario());
				user.get().setSenhaUsuario(usuario.get().getSenhaUsuario());
				user.get().setIdUsuario(usuario.get().getIdUsuario());
				user.get().setNomeUsuario(usuario.get().getNomeUsuario());

				return user;

			}
		}
		return null;
	}

}