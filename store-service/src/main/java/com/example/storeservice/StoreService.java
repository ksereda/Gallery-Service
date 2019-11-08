package com.example.storeservice;

import com.example.storeservice.model.Order;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

@Service
@EnableBinding(Sink.class)
@MessageEndpoint
public class StoreService {

	Logger logger = java.util.logging.Logger.getLogger(StoreService.class.getName());

	@StreamListener(Sink.INPUT)
	public void logMessage(Order order) {
		logger.info("Order processing: " + order);
	}

}
