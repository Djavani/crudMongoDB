package com.djavani.crud.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.djavani.crud.api.models.Produto;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
	
	public List<Produto> findAllBy(TextCriteria criteria, Pageable pages);

	public List<Produto> findByNomeLikeIgnoreCase(String nome);

}
