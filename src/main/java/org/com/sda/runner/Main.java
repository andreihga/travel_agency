package org.com.sda.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("org.com.sda.*")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}