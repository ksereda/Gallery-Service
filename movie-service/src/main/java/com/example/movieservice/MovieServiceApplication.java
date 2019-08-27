package com.example.movieservice;

import com.example.movieservice.model.Movie;
import com.example.movieservice.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableEurekaClient
@EnableReactiveMongoRepositories
public class MovieServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieServiceApplication.class, args);
	}

	// This code creates a Flux of four sample Persons objects, saves them to the DB. Then, queries all the Persons from the DB and print them to the console.
	@Bean
	CommandLineRunner run(MovieRepository movieRepository) {
		return args -> {
			movieRepository.deleteAll()
					.thenMany(Flux.just(
							new Movie("1", "Lion King", "drama, family", "Jon Favreau", "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/the-lion-king-mufasa-simba-1554901700.jpg?crop=0.533xw:1.00xh;0.230xw,0&resize=480:*"),
							new Movie("2", "Saw", "horror", "james Van", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnq84aiBWKZx-YzrvC59J8EN74JYV3sfqu_Tdxe-m5rn9u_zD0"),
							new Movie("3", "Home Alone", "comedy", "Chris Columbus", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHGOxq3IPpv_OuWuV8BCEBq0uJ9VCRsGjrqxGTXxWI81bu1vCQ")
							)
							.flatMap(movieRepository::save))
					.thenMany(movieRepository.findAll())
					.subscribe(System.out::println);

		};
	}

}
