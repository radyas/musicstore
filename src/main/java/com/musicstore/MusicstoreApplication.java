package com.musicstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.musicstore"})
public class MusicstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicstoreApplication.class, args);
    }

}
