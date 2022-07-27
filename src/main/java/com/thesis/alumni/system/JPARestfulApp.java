package com.thesis.alumni.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JPARestfulApp {

    public static void main(String[] args) {
        SpringApplication.run(JPARestfulApp.class, args);
    }

}
