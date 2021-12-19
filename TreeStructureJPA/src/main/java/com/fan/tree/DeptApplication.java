package com.fan.tree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.fan.tree.repository")
@SpringBootApplication
public class DeptApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeptApplication.class, args);
    }
}
