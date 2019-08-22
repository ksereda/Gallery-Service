//package com.example.galleryservice.service;
//
//import com.example.galleryservice.model.Bucket;
//import com.example.galleryservice.repository.SimpleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class BucketService {
//
//    @Autowired
//    SimpleRepository repository;
//
//    public List<Bucket> getAllEmployees() {
//        List<Bucket> result = (List<Bucket>) repository.findAll();
//
//        if (result.size() > 0) {
//            return result;
//        } else {
//            return new ArrayList<>();
//        }
//
//    }
//
//}
