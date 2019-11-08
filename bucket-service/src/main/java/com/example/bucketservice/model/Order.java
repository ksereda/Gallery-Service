package com.example.bucketservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {

    private final int id;
    private final String type;
    private final String name;
    private final String status;

}
