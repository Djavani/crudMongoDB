package com.djavani.crud.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.djavani.crud.api.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
