package com.example.galleryservice;

import com.example.galleryservice.model.Bucket;
import com.example.galleryservice.repository.BucketRepository;
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
public class GalleryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GalleryServiceApplication.class, args);
	}

	// This code creates a Flux of four sample Persons objects, saves them to the DB. Then, queries all the Persons from the DB and print them to the console.
	@Bean
	CommandLineRunner run(BucketRepository bucketRepository) {
		return args -> {
			bucketRepository.deleteAll()
					.thenMany(Flux.just(
							new Bucket("1", "Java", "OOP", 280, "http://infopulse-univer.com.ua/images/trenings/java.png"),
							new Bucket("2", "Java", "Steram API", 437, "https://www.hdwallpaperslife.com/wp-content/uploads/2018/09/JAVA14-480x270.png"),
							new Bucket("3", "Java", "Collections", 14, "https://i.ytimg.com/vi/oOOESCvGGcI/hqdefault.jpg"),
							new Bucket("4", ".NET", "Basic", 1213, "https://upload.wikimedia.org/wikipedia/commons/0/0e/Microsoft_.NET_logo.png"),
							new Bucket("5", "C++", "Basic", 870, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSmgIz9Ug-MVzBQJMcgXedOXTqHWGmbSu5pPDivz8hrfo_GE0HZEA"),
							new Bucket("6", "JavaScript", "Angular 8", 155, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTg41zepuyHbew8bIsTYeKWJ9RYOnnV922lNa85-fQTVrKDG19K2w")
					)
							.flatMap(bucketRepository::save))
					.thenMany(bucketRepository.findAll())
					.subscribe(System.out::println);

		};
	}

}
