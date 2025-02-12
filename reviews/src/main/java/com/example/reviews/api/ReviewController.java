package com.example.reviews.api;

import com.example.reviews.domain.Review;
import com.example.reviews.domain.ReviewRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviews;

    public ReviewController(ReviewRepository reviews) {
        this.reviews = reviews;
    }

    @GetMapping
    public List<ReviewModel> findByProductId(@RequestParam long productId) {
        return reviews.getAll()
                .stream()
                .filter(review -> review.getProductId().equals(productId))
                .map(ReviewModel::of)
                .collect(Collectors.toList());
    }
}
