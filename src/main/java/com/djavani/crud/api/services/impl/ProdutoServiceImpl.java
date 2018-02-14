package com.djavani.crud.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djavani.crud.api.models.Produto;
import com.djavani.crud.api.repositories.ProdutoRepository;
import com.djavani.crud.api.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository ProdutoRepository;
	
	@Override
	public List<Produto> listarTodos() {		
		return this.ProdutoRepository.findAll();
	}

	@Override
	public Produto listarPorId(String id) {		
		return this.ProdutoRepository.findOne(id);
	}

	@Override
	public Produto cadastrar(Produto produto) {
		produto.setCategoria(produto.getCategoria());
		return this.ProdutoRepository.save(produto);
	}

	@Override
	public Produto atualizar(Produto produto) {		
		return this.ProdutoRepository.save(produto);
	}

	@Override
	public void remover(String id) {
		this.ProdutoRepository.delete(id);

	}

}
