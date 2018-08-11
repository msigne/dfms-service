package com.ia.dfms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.kaiso.relmongo.config.EnableRelationalMongo;

@SpringBootApplication
@EnableRelationalMongo
public class DfmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfmsApplication.class, args);
    }
}
