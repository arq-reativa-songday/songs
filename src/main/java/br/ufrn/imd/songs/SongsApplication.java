package br.ufrn.imd.songs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication()
public class SongsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SongsApplication.class, args);
    }

}
