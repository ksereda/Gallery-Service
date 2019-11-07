package com.example.userservice.service;

import com.example.userservice.controller.UserController;
import com.example.userservice.model.Bucket;
import org.apache.log4j.Level;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class Fallback implements ServiceFeignClient {

//    private static final Logger LOG = Logger.getLogger(TestService.class.getName());
    Logger logger = java.util.logging.Logger.getLogger(Fallback.class.getName());

    @Override
    public List<Bucket> getAllEmployeesList() {
//        LOG.log(Level.INFO, "ERROR: Service is not available now");
        logger.info("ERROR: Service is not available now");
        return new ArrayList<>();
    }

}
