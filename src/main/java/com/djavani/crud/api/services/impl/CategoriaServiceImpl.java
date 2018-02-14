package com.djavani.crud.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djavani.crud.api.models.Categoria;
import com.djavani.crud.api.repositories.CategoriaRepository;
import com.djavani.crud.api.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository CategoriaRepository;
	
	@Override
	public List<Categoria> listarTodos() {		
		return this.CategoriaRepository.findAll();
	}

	@Override
	public Categoria listarPorId(String id) {		
		return this.CategoriaRepository.findOne(id);
	}

	@Override
	public Categoria cadastrar(Categoria categoria) {	
		return this.CategoriaRepository.save(categoria);
	}

	@Override
	public Categoria atualizar(Categoria categoria) {		
		return this.CategoriaRepository.save(categoria);
	}

	@Override
	public void remover(String id) {
		this.CategoriaRepository.delete(id);

	}

}
