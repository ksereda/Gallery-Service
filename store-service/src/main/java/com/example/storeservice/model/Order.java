package com.example.storeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "orders")
public class Order {

    private final int id;
    private final String type;
    private final String name;
    private final String status;

}
