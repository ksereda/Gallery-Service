package com.example.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//@Data
//@Builder
//@AllArgsConstructor
@Document(collection = "buckets")
public class Bucket {

    @Id
    private String id;

    @NotBlank
    @Size(max = 10)
    private String title;

    private String description;
    private int personalNumber;
    private String imageLink;

    public Bucket() {
    }

    public Bucket(String id, @NotBlank @Size(max = 10) String title, String description, int personalNumber, String imageLink) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.personalNumber = personalNumber;
        this.imageLink = imageLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(int personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
