package com.example.products.api;

import com.example.products.client.reviews.ReviewClient;
import com.example.products.client.reviews.ReviewModel;
import com.example.products.domain.Product;
import com.example.products.domain.ProductRepository;
import com.example.products.infra.database.ProductRepositoryImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository products = new ProductRepositoryImpl();

    private final ReviewClient reviewClient;


    public ProductController( ReviewClient reviewClient) {
        this.reviewClient = reviewClient;
    }

    @GetMapping
    public List<ProductModel> buscarTodos() {
        return products.getAll()
                .stream()
                .map(this::convertProductToModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public ProductModel buscarPorId(@PathVariable Long produtoId) {
        return products.getOne(produtoId)
                .map(this::convertProductToModelWithReview)
                .orElseThrow(ResourceNotFoundException::new);
    }

    private ProductModel convertProductToModel(Product produto) {
        return ProductModel.of(produto);
    }

    private ProductModel convertProductToModelWithReview(Product produto) {
        return ProductModel.of(produto, findReviewOfProduct(produto.getId()));
    }

    private List<ReviewModel> findReviewOfProduct(Long productId) {
        return reviewClient.findAllByProductId(productId);
    }
}
