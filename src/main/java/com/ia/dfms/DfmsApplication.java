package com.ia.dfms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DfmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfmsApplication.class, args);
    }
}
