package com.personalblog.blogisa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalblog.blogisa.model.Postagem;
import com.personalblog.blogisa.repository.PostagemRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired 
	private PostagemRepository repositorio;
	
	@GetMapping("/todas")
	public ResponseEntity<List<Postagem>> getAll() {
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping("/idPostagem/{idPostagem}")
	public ResponseEntity<Postagem> getById(@PathVariable(value = "id_postagem") Long idPostagem) {
		Optional<Postagem> objetoPostagem = repositorio.findById(idPostagem);
		if(objetoPostagem.isPresent()) {
			return ResponseEntity.status(200).body(objetoPostagem.get());		
			}else {
				return ResponseEntity.status(204).build();
			}
	}
	
	@GetMapping("/todosNomes/{tituloPostagem}")
	public ResponseEntity<List<Postagem>> getAllByNome(@PathVariable(value = "tituloPostagem") String tituloPostagem) {
		List<Postagem> objetoNomes = repositorio.findAllByTituloPostagemContainingIgnoreCase(tituloPostagem);
		if(objetoNomes.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoNomes);
		}
	}
 	
	@PostMapping("/novapostagem")
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repositorio.save(postagem));
	}
	
	
	@PutMapping("/atualizapostagem")
	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) {
		return ResponseEntity.ok(repositorio.save(postagem));
	}
	
	@DeleteMapping("/deletepostagem/{idPostagem}")
	public void delete(@PathVariable Long idPostagem) {
		repositorio.deleteById(idPostagem);
	}
	
}
