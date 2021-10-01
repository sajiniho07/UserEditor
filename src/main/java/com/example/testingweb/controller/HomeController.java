package com.example.testingweb.controller;

import com.example.testingweb.helper.EmployeeRepository;
import com.example.testingweb.model.Employee;
import com.example.testingweb.model.SampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
public class HomeController {

    @Autowired
    private final EmployeeRepository repository;

    HomeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/simpleResponse")
    public SampleResponse simpleResponse(
            @RequestParam(value = "name", defaultValue = "unknown") String name) {
        SampleResponse response = new SampleResponse();
        response.setId(1);
        response.setMessage("Your name is " + name);
        return response;
    }

    @GetMapping("/findAll")
    ResponseEntity<CollectionModel<EntityModel<Employee>>> findAll() {
        Stream<Employee> stream = StreamSupport.stream(repository.findAll().spliterator(), false);
        Stream<EntityModel<Employee>> entityModelStream = stream.map(employee -> EntityModel.of(employee));
        List<EntityModel<Employee>> employees = entityModelStream.collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(employees));
    }

    @GetMapping("/findOne/{id}")
    ResponseEntity<EntityModel<Employee>> findOne(@PathVariable long id) {
        Optional<Employee> byId = repository.findById(id);
        Optional<EntityModel<Employee>> employeeEntityModel = byId.map(employee -> EntityModel.of(employee));

        return employeeEntityModel
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/newEmployee")
    ResponseEntity<?> newEmployee(@RequestBody Employee employee) {
        try {
            Employee savedEmployee = repository.save(employee);
            return findOne(savedEmployee.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unable to create " + employee);
        }
    }

    @PutMapping("/updateEmployee/{id}")
    ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {
        Employee employeeToUpdate = employee;
        employeeToUpdate.setId(id);
        try {
            repository.save(employeeToUpdate);
            return findOne(employeeToUpdate.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Unable to update " + employeeToUpdate);
        }
    }
}