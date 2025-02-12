package com.example.reviews.infra.database;

import com.example.reviews.domain.Review;
import com.example.reviews.domain.ReviewRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReviewRepositoryImpl implements ReviewRepository {

    private static final List<Review> REVIEWS = new ArrayList<>();
    private static long id = 1;

    // Initialize the statics
    static {
        REVIEWS.add(new Review(nextId(), 10, "Thiago",
                "perfect.", 1L));
        REVIEWS.add(new Review(nextId(), 1, "Alexandre",
                "terrible.", 1L));
        REVIEWS.add(new Review(nextId(), 4, "Maria",
                "The computer is freezing.", 1L));

        REVIEWS.add(new Review(nextId(), 8, "Daniel",
                "almost perfect.", 2L));
        REVIEWS.add(new Review(nextId(), 5, "Alex",
                "Bad.", 3L));
    }

    @Override
    public void save(Review review) {
        review.setId(nextId());
        REVIEWS.add(review);
    }

    @Override
    public Optional<Review> getOne(Long id) {
        return REVIEWS.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Review> getAll() {
        return new ArrayList<>(REVIEWS);
    }

    private static long nextId() {
        return id++;
    }

}
