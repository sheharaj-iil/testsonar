package com.informaticsint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author : sheharaj
 * Informatics International Limited. All Rights Reserved
 */

@SpringBootApplication
@EnableMongoAuditing
@ComponentScan({"com.informaticsint", "com.informaticsint.starter"})
@EnableMongoRepositories
public class EnicAdminServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnicAdminServiceApplication.class, args);
    }

}
