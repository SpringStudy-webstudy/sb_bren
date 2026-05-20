package com.study.spring1team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Spring1teamApplication {

    public static void main(String[] args) {
        SpringApplication.run(Spring1teamApplication.class, args);
    }

}
