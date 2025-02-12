package com.example.products.client.reviews;

import java.util.List;

public interface ReviewClient {

    List<ReviewModel> findAllByProductId(Long productId);

}
