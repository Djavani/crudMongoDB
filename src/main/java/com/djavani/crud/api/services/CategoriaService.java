package com.djavani.crud.api.services;

import java.util.List;

import com.djavani.crud.api.models.Categoria;

public interface CategoriaService {
	
	List<Categoria> listarTodos();
	
	Categoria listarPorId(String id);
	
	Categoria cadastrar(Categoria Categoria);
	
	Categoria atualizar(Categoria Categoria);
	
	void remover(String id);

}
