package com.example.userservice.service;

import com.example.userservice.model.Bucket;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Fallback implements ServiceFeignClient {

    @Override
    public List<Bucket> getAllEmployeesList() {
        return new ArrayList<>();
    }

}
