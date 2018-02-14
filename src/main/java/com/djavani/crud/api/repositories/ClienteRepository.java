package com.djavani.crud.api.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.djavani.crud.api.documents.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
	
	public List<Cliente> findAllBy(TextCriteria criteria, Pageable pages);

	public List<Cliente> findByNomeLikeIgnoreCase(String nome);

}
