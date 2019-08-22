package com.example.galleryservice.repository;

import com.example.galleryservice.model.Bucket;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends ReactiveMongoRepository<Bucket, String> {
}
