package com.example.filterservice;

import com.example.filterservice.model.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilterServiceApplication.class, args);
	}

	private static final String ORIGINAL_SUBSCRIBER_QUEUE_NAME = "inputSubscriber.inputSubscriberGroup";
	private static final String SUBSCRIBER_DLQ_NAME = ORIGINAL_SUBSCRIBER_QUEUE_NAME + ".dlq";

	@Autowired
	RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = {SUBSCRIBER_DLQ_NAME})
	public void processFailedMessage(Message failedMessage){
		System.out.println("This is very bad words " + failedMessage.toString());
	}

}
