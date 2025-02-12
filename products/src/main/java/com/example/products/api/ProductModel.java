package com.example.products.api;

import com.example.products.client.reviews.ReviewModel;
import com.example.products.domain.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ProductModel {

    public Long id;

    public String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<ReviewModel> reviews;

    public ProductModel() {
    }

    public ProductModel(Long id, String name, List<ReviewModel> reviews) {
        this.id = id;
        this.name = name;
        this.reviews = reviews;
    }

    public ProductModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ProductModel of(Product product) {
        return new ProductModel(
                product.getId(),
                product.getName()
        );
    }

    public static ProductModel of(Product product, List<ReviewModel> reviewModels) {
        return new ProductModel(
                product.getId(),
                product.getName(),
                reviewModels
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ReviewModel> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewModel> reviews) {
        this.reviews = reviews;
    }
}
