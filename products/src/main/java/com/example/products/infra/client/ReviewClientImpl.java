package com.example.products.infra.client;

import com.example.products.client.reviews.ReviewClient;
import com.example.products.client.reviews.ReviewModel;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Component
public class ReviewClientImpl implements ReviewClient {

    private final Logger logger = LoggerFactory.getLogger(ReviewClientImpl.class);
    private final RestTemplate restTemplate;

    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8081/reviews")
            .queryParam("productId", "{productId}")
            .encode()
            .toUriString();

    public ReviewClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final Map<Long, List<ReviewModel>> CACHE = new HashMap<>();

    @Override
    @CircuitBreaker(name="reviewCB", fallbackMethod = "findAllproductIdInCache")
    public List<ReviewModel> findAllByProductId(Long produtoId) {
        final List<ReviewModel> avaliacoes = executeReq(produtoId);
        return avaliacoes;
    }

    private List<ReviewModel> executeReq(Long productId) {
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("productId", productId);

        logger.info("searching for reviews by productId: " + productId);
        final ReviewModel[] avaliacoes;

        try {
            avaliacoes = restTemplate.getForObject(API_URL, ReviewModel[].class, parametros);
        } catch (Exception e) {
            logger.error("Error to find reviews");
            throw e;
        }

        logger.info("charging cache");
        CACHE.put(productId, Arrays.asList(avaliacoes));

        return Arrays.asList(avaliacoes);
    }

    private List<ReviewModel> findAllproductIdInCache(Long productId, Throwable e) {
        logger.info("searching for reviews by products in cache");
        return CACHE.getOrDefault(productId, new ArrayList<>());
    }
}
