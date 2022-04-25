package com.example.testingweb.repository;

import com.example.testingweb.bean.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {}
