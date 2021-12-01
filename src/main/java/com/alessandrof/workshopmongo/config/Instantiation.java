package com.alessandrof.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.alessandrof.workshopmongo.domain.Post;
import com.alessandrof.workshopmongo.domain.User;
import com.alessandrof.workshopmongo.dto.AuthorDTO;
import com.alessandrof.workshopmongo.dto.CommentDTO;
import com.alessandrof.workshopmongo.repository.PostRepository;
import com.alessandrof.workshopmongo.repository.UserRepository;


@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GM"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para SP. Hug", new AuthorDTO(maria));
		Post p2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Acordei feliz", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viajem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("have a good travel!", sdf.parse("23/03/2018"), new AuthorDTO(maria));
		
		p1.getComments().addAll(Arrays.asList(c1, c2));
		p2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(p1, p2));
		
		maria.getPost().addAll(Arrays.asList(p1, p2));
		
		userRepository.save(maria);
		
		
		
	}
	

}
