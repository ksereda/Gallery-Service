package com.example.userservice.service;

import com.example.userservice.error.MyCustomClientException;
import com.example.userservice.error.MyCustomServerException;
import com.example.userservice.model.Bucket;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

    private static final String API_MIME_TYPE = "application/json";
    private static final String API_BASE_URL = "http://localhost:8081";
    private static final String USER_AGENT = "User Service";
    private static final Logger logger = LoggerFactory.getLogger(WebClientService.class);

    private WebClient webClient;

    public WebClientService() {
        this.webClient = WebClient.builder()
                .baseUrl(API_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, API_MIME_TYPE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
//                .filter(WebClientService.errorHandlingFilter())  // хз
                .build();
    }

    public Flux<Bucket> getDataByWebClient() {
        return webClient
                .get()
                .uri("/stream/buckets/delay")
                .exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Bucket.class));
    }



//    public Flux<Bucket> getDataByWebClient() {
//        return webClient
//                .get()
//                .uri("/getAll")
//                .retrieve()
////                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
////                        Mono.error(new RuntimeException("4xx"))
////                )
////                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
////                        Mono.error(new RuntimeException("5xx"))
////                )
////                .onStatus(HttpStatus::is3xxRedirection, clientResponse ->
////                        Mono.error(new MyCustomServerException())
////                )
////                .onStatus(HttpStatus::isError, clientResponse ->
////                        Mono.error(new MyCustomServerException())
////                )
//
//                .onStatus(HttpStatus::is4xxClientError, response -> {
//                    System.out.println("4xx eror");
//                    return Mono.error(new RuntimeException("4xx"));
//                })
//                .onStatus(HttpStatus::is5xxServerError, response -> {
//                    System.out.println("5xx eror");
//                    return Mono.error(new RuntimeException("5xx"));
//                })
//
//                .onStatus(HttpStatus::isError, clientResponse -> {
//                    System.out.println("eror");
//                    return Mono.error(new MyCustomServerException());
//                })
//
//                .bodyToFlux(Bucket.class);
//    }






//     public Flux<Bucket> getDataByWebClient() {
//        return webClient
//                .get()
//                .uri("/getAll")
//                .exchange()
//                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Bucket.class));
//    }
//
//    public static ExchangeFilterFunction errorHandlingFilter() {
//        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
//            if(clientResponse.statusCode()!=null && (clientResponse.statusCode().is5xxServerError() || clientResponse.statusCode().is4xxClientError()) ) {
//                return clientResponse.bodyToMono(String.class)
//                        .flatMap(errorBody -> {
////                            return Mono.error(new CustomWebClientResponseException(errorBody,clientResponse.statusCode()));
//                            return Mono.error(new MyCustomServerException());
//                        });
//            }else {
//                return Mono.just(clientResponse);
//            }
//        });
//    }


}
