package com.example.loginservice.controller;

import com.example.loginservice.model.LoginForm;
import com.example.loginservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableBinding(Source.class)
public class LoginController {

    @Autowired
    private Source source;

    @Autowired
    private LoginService loginService;

    @RequestMapping(path = "/sendLogin", method = RequestMethod.POST)
    @ResponseBody
    public String orderFood(@RequestBody LoginForm message) {
        loginService.createOrUpdateEmployee(message);
        source.output().send(MessageBuilder.withPayload(message).build());
        System.out.println(message.toString());
        return "Login form created successfully!";
    }

//    @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
//    public String createOrUpdateEmployee(LoginForm employee) {
//        loginService.createOrUpdateEmployee(employee);
//        return "redirect:/";
//    }

//    @RequestMapping("/order")
//    @ResponseBody
//    public String orderFood2(@RequestBody LoginForm foodOrder) {
//        source.output().send(MessageBuilder.withPayload(foodOrder).build());
//        System.out.println(foodOrder.toString());
//        return "food ordered!";
//    }

}
