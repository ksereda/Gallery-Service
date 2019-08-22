package com.example.userservice.controller;

import com.example.userservice.model.Bucket;
import com.example.userservice.service.ServiceFeignClient;
import com.example.userservice.service.TestService;
import com.example.userservice.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private TestService service;

    @Autowired
    private WebClientService webClientService;

    @RequestMapping("/")
    public String home() {
        return "Hello from User-Service running at port: " + env.getProperty("local.server.port");
    }

    // Using Feign Client
    @RequestMapping(path = "/getAllDataFromGalleryService")
    public List<Bucket> getDataByFeignClient(Model model) {
        List<Bucket> list = ServiceFeignClient.FeignHolder.create().getAllEmployeesList();
//        model.addAttribute("employees", list);
//        return "resultlist-employees";
        return list;
    }

    // Using RestTemplate
    @GetMapping("/data")
    public String data(){
        return service.data();
    }

    // Using WebClient
    @GetMapping(value = "/getDataByWebClient",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Bucket> getDataByWebClient() {
        return webClientService.getDataByWebClient();
    }

}
