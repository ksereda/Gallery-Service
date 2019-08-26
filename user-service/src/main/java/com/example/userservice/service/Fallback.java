package com.example.userservice.service;

import com.example.userservice.model.Bucket;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Fallback implements ServiceFeignClient {

    private static final Logger LOG = Logger.getLogger(TestService.class.getName());


    @Override
    public List<Bucket> getAllEmployeesList() {
        LOG.log(Level.INFO, "ERROR: Service is not available now");
        return new ArrayList<>();
    }

}
