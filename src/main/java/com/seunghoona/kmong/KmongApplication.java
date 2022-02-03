package com.seunghoona.kmong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class KmongApplication {

    public static void main(String[] args) {
        SpringApplication.run(KmongApplication.class, args);
    }

}
