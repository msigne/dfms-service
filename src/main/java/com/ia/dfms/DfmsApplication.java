package com.ia.dfms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import io.github.kaiso.relmongo.config.EnableRelationalMongo;

@SpringBootApplication
@EnableRelationalMongo
@EnableMongoAuditing
@EnableReactiveMongoRepositories
public class DfmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DfmsApplication.class, args);
    }
}
