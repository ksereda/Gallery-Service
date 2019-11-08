package com.example.bucketservice.service;

import com.example.bucketservice.model.Order;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Processor.class)
@MessageEndpoint
public class CheckOrderService {

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    Order transform(Order order) {

        Order checkedOrder = new Order(order.getId(),
                order.getType(),
                order.getName(),
                "checked");

        System.out.println(checkedOrder);

        return checkedOrder;
    }

}
