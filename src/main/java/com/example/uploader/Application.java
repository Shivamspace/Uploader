package com.example.uploader;

import org.slf4j.*;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
