package com.djavani.crud.api.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.djavani.crud.api.dto.AuthorDTO;
import com.djavani.crud.api.dto.CommentDTO;
import com.djavani.crud.api.models.Post;
import com.djavani.crud.api.models.User;
import com.djavani.crud.api.repositories.PostRepository;
import com.djavani.crud.api.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postReposiroty;
	
	@Override
	public void run(String... arg0) throws Exception {		
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		postReposiroty.deleteAll();
		userRepository.deleteAll();

		User maria = new User(null, "Maria Jose", "maria@gmail.com");
		User alex = new User(null, "Alex Santos Silva", "maria@gmail.com");
		User antonio = new User(null, "Antonio Jose Fortes Silva", "maria@gmail.com");
		 
		userRepository.save(Arrays.asList(maria, alex, antonio));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(antonio));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
				
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postReposiroty.save(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
