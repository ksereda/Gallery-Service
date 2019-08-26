package com.example.userservice.controller;

import com.example.userservice.error.MyCustomServerException;
import com.example.userservice.model.Bucket;
import com.example.userservice.service.ServiceFeignClient;
import com.example.userservice.service.TestService;
import com.example.userservice.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

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
        String home = "User-Service running at port: " + env.getProperty("local.server.port");
        LOG.log(Level.INFO, home);
        return home;
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

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<MyCustomServerException> handleWebClientResponseException(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MyCustomServerException("A Bucket with the same title already exists"));
    }

}
