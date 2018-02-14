package com.djavani.crud.api.services;

import java.util.List;

import com.djavani.crud.api.models.Produto;

public interface ProdutoService {
	
	List<Produto> listarTodos();
	
	Produto listarPorId(String id);
	
	Produto cadastrar(Produto Produto);
	
	Produto atualizar(Produto Produto);
	
	void remover(String id);

}
