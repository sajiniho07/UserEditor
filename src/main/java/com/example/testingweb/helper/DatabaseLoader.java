package com.example.testingweb.helper;

import com.example.testingweb.models.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {

	@Bean
	CommandLineRunner init(EmployeeRepository repository) {
		return args -> {
			repository.save(new Employee("sajad", "kamali", "waiter"));
			repository.save(new Employee("hasan", "ahmadi", "boss"));
		};
	}

}
