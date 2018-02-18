package com.djavani.crud.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djavani.crud.api.dto.UserDTO;
import com.djavani.crud.api.models.User;
import com.djavani.crud.api.repositories.UserRepository;
import com.djavani.crud.api.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}

	public User insert(User obj) {
		return userRepository.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		userRepository.delete(id);
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

	public User update(User user) {
		User newObj = userRepository.findOne(user.getId());
		updateData(newObj, user);
		return userRepository.save(newObj);
	}

	private void updateData(User newObj, User user) {
		newObj.setName(user.getName());
		newObj.setEmail(user.getEmail());
	}

}
