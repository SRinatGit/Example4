package ru.example4.spring;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "ru.example4.spring")
public class Start {
    private static final Logger logger = Logger.getLogger(Start.class);

    public static void main(String[] args) {
        logger.info("START READER DATA.CSV");

        ConfigurableApplicationContext context = SpringApplication.run(Start.class, args);

        logger.info("FINISH READER DATA.CSV");

        context.close();
    }
}
