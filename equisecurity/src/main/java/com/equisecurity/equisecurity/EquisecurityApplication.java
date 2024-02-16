package com.equisecurity.equisecurity;

import com.equisecurity.equisecurity.model.Role;
import com.equisecurity.equisecurity.model.User;
import com.equisecurity.equisecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EquisecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(EquisecurityApplication.class, args);}
	public void run(String...args) {
				User adminAccount = userRepository.findByRole(Role.ADMIN);
				if(null==adminAccount){
					User user= new User();
					user.setEmail("admin@gmail.com");
					user.setFirstName("admin");
					user.setLastName("admin");
					user.setRole(Role.ADMIN);
					user.setUsername("admin");
					user.setPassword(new BCryptPasswordEncoder().encode("admin"));
					userRepository.save(user);
				}

			}
		}




