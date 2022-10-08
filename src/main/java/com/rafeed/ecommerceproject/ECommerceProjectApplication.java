package com.rafeed.ecommerceproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class ECommerceProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceProjectApplication.class, args);
    }

}
