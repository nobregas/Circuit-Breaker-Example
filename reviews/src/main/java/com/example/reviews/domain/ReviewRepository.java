package com.example.reviews.domain;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    void save(Review review);

    Optional<Review> getOne(Long id);

    List<Review> getAll();
}
