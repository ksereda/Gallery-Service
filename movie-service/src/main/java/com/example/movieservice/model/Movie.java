package com.example.movieservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "movies")
public class Movie {

    @Id
    private String id;

    @NotBlank
    @Size(max = 10)
    private String movieTitle;

    private String genre;
    private String producer;
    private String imageLink;

}
