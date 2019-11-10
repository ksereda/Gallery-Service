package com.example.filterservice;

import com.example.filterservice.model.LoginForm;
import com.example.filterservice.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class FilterServiceApplication {

	@Autowired
	private LoginService loginService;

	public static void main(String[] args) {
		SpringApplication.run(FilterServiceApplication.class, args);
	}

	private static final String ORIGINAL_SUBSCRIBER_QUEUE_NAME = "inputSubscriber.inputSubscriberGroup";
	private static final String SUBSCRIBER_DLQ_NAME = ORIGINAL_SUBSCRIBER_QUEUE_NAME + ".dlq";

	@StreamListener(target = Sink.INPUT)
	public void handleMessage(LoginForm message) throws Exception {
		loginService.handle(message);
	}
}
