package com.example.testingweb.helper;

import com.example.testingweb.bean.User;
import com.example.testingweb.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {

	/*@Bean
	CommandLineRunner init(UserRepository repository) {
		return args -> {
			repository.save(new User("sajad", "kamali", "waiter", "1234"));
			repository.save(new User("hasan", "ahmadi", "boss", "pas123"));
		};
	}*/

}
