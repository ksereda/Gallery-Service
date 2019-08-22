package com.example.userservice.controller;

import com.example.userservice.model.Bucket;
import com.example.userservice.model.Employee;
import com.example.userservice.service.ServiceFeignClient;
import com.example.userservice.service.TestService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private Environment env;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TestService service;

    @RequestMapping("/")
    public String home() {
        // This is useful for debugging
        // When having multiple instance of gallery service running at different ports.
        // We load balance among them, and display which instance received the request.
        return "Hello from User-Service running at port: " + env.getProperty("local.server.port");
    }

    // Using Feign Client
    @RequestMapping(path = "/getAllDataFromGalleryService")
    public List<Bucket> getData(Model model) {
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

}
