package com.example.reviews.api;

import com.example.reviews.domain.Review;

public class ReviewModel {

    private Long id;

    private Integer rating;

    private String description;

    private String author;

    public ReviewModel() {
    }

    public ReviewModel(Long id, Integer rating, String description, String author) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static ReviewModel of(Review review) {
        return new ReviewModel(
                review.getId(),
                review.getRating(),
                review.getDescription(),
                review.getAuthor()
        );
    }
}
