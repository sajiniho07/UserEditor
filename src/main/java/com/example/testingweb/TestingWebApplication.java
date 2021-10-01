package com.example.testingweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;

@SpringBootApplication
public class TestingWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingWebApplication.class, args);
    }

    @Bean
    EvoInflectorLinkRelationProvider relProvider() {
        return new EvoInflectorLinkRelationProvider();
    }
}