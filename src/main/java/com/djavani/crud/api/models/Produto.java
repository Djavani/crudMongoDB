package com.djavani.crud.api.models;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Document
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@NotEmpty(message = "Nome não pode ser vazio")
	@TextIndexed
	private String nome;
	
	@NotEmpty(message = "Descrição não pode ser vazio")
	@TextIndexed
	private String descricao;
	
	@DBRef
	private Categoria categoria;
		
	@TextScore
	private Float score;

	public Produto() {
	
	}
			
	public Produto(String id, String nome, String descricao, Categoria categoria, Float score) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	
	
}
