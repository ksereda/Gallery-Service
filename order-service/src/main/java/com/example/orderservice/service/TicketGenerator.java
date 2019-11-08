package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class TicketGenerator {

    private final Random rnd = new Random();

    public Order createOrderForSend() {
        int randomId = rnd.nextInt(10);
        String type = "ticket";
        String randomName = "payment order: " + randomId;
        return new Order(randomId, type, randomName);
    }
}
