package com.example.movieservice.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String bucketId) {
        super("Bucket not found with id " + bucketId);
    }
}
