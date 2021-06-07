package com.example.uploader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
//        logger.info("this is a info message");
//        logger.warn("this is a warn message");
//        logger.error("this is a error message");
        SpringApplication.run(Application.class, args);
    }
}
