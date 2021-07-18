package com.example.user.repository;


import com.example.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new User("Mirella Nobrega", "mirella", "12345", "eltilopes@gmail.com", false)));
            log.info("Preloading " + repository.save(new User("Melissa Lopes", "melissa", "12345", "eltilopes@gmail.com", false)));
            log.info("Preloading " + repository.save(new User("Elton Lopes", "elton", "12345", "eltilopes@gmail.com", true)));
        };
    }
}