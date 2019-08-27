package com.example.userservice;

import com.example.userservice.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableHystrix
@RibbonClient(name = "movie-service", configuration = RibbonConfiguration.class)
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	// Create a bean for restTemplate to call services
	@Bean
	@LoadBalanced   // Load balance between service instances running at different ports.
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// for using WebClient
	public @Bean
	WebClient webClient() {
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector()).baseUrl("http://localhost:8081").build();
	}

}
