package com.example.reviews.domain;

public class Review {

    public Long id;

    public Integer rating;

    public String description;

    public String author;

    public Long productId;

    public Review() {
    }

    public Review(Long id, Integer rating, String description, String author, Long productId) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.author = author;
        this.productId = productId;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
