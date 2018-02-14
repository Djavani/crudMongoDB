package com.djavani.crud.api.models;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

@Document
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	@NotEmpty(message = "Nome não pode ser vazio")
	@TextIndexed
	private String nome;
	
	@NotEmpty(message = "Descricao não pode ser vazio")
	@TextIndexed
	private String descricao;
	
	
	@TextScore
	private Float score;


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


	public Float getScore() {
		return score;
	}


	public void setScore(Float score) {
		this.score = score;
	}
	
	
	
	

}
