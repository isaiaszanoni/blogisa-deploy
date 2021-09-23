package com.personalblog.blogisa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "tb_postagens")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPostagem;
	
	@NotNull
	private String tituloPostagem;
	
	@NotNull
	private String descricaoPostagem;
	
	@NotNull
	private String textoPostagem;
	
	@ManyToOne
	@JoinColumn(name = "criador_id")
	@JsonIgnoreProperties({"minhasPostagens"})
	private Usuario criador;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema temaRelacionado;
	
	// Getters and Setters

	public Long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(Long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getTituloPostagem() {
		return tituloPostagem;
	}

	public void setTituloPostagem(String tituloPostagem) {
		this.tituloPostagem = tituloPostagem;
	}

	public String getDescricaoPostagem() {
		return descricaoPostagem;
	}

	public void setDescricaoPostagem(String descricaoPostagem) {
		this.descricaoPostagem = descricaoPostagem;
	}

	public String getTextoPostagem() {
		return textoPostagem;
	}

	public void setTextoPostagem(String textoPostagem) {
		this.textoPostagem = textoPostagem;
	}

	public Tema getTemaRelacionado() {
		return temaRelacionado;
	}

	public void setTemaRelacionado(Tema temaRelacionado) {
		this.temaRelacionado = temaRelacionado;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}
	
}
