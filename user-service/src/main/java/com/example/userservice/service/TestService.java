package com.example.userservice.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

@Service
public class TestService {

    private static final Logger LOG = Logger.getLogger(TestService.class.getName());

//    @Autowired
//    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate template;

    @HystrixCommand(fallbackMethod = "failed")
    public String data() {
//        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka("gallery-service", false);
//        String serviceBaseUrl = instanceInfo.getHomePageUrl();
//        String result = new RestTemplate().getForObject(serviceBaseUrl + "data", String.class);
//        return result;
        String response = template.getForObject("http://gallery-service/data", String.class);
        LOG.log(Level.INFO, response);
        return response;
    }

    public String failed() {
        String error = "Service is not available now. Please try later";
        LOG.log(Level.INFO, error);
        return error;
    }

}
