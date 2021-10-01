package com.example.testingweb.helper;

import com.example.testingweb.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {}
