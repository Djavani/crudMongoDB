package com.djavani.crud.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.djavani.crud.api.documents.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

}
