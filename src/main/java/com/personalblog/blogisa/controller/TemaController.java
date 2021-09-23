package com.personalblog.blogisa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personalblog.blogisa.model.Tema;
import com.personalblog.blogisa.repository.TemaRepository;

// controller temas
@RestController
@RequestMapping("/temas")
public class TemaController {
	
	@Autowired 
	private TemaRepository repositorio;
	
	@GetMapping("/todos")
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Tema> post(@RequestBody Tema tema) {
		return ResponseEntity.ok(repositorio.save(tema));
	}
	
	@DeleteMapping("/delete/{idTema}")
	public void delete(@PathVariable Long idTema) {
		repositorio.deleteById(idTema);
	}
}
