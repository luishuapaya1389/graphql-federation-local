package com.example.reviews.fetcher;

import com.example.reviews.model.ProductRef;
import com.example.reviews.model.Review;
import com.netflix.graphql.dgs.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class ReviewDataFetcher {

    private static final List<Review> REVIEWS = List.of(
            new Review("1", "Laptop increíble, muy rápida", 5, "Alice", "1"),
            new Review("2", "Buen mouse, algo caro", 4, "Bob", "2"),
            new Review("3", "El escritorio cambió mi vida", 5, "Carlos", "3"),
            new Review("4", "La laptop se calienta un poco", 3, "Diana", "1"),
            new Review("5", "Audífonos con sonido excepcional", 5, "Eve", "4"));

    @DgsQuery
    public List<Review> reviews() {
        return REVIEWS;
    }

    @DgsQuery
    public List<Review> reviewsByProduct(@InputArgument String productId) {
        return REVIEWS.stream()
                .filter(r -> r.productId().equals(productId))
                .collect(Collectors.toList());
    }

    /**
     * Resolver del campo `product` en Review.
     * Retorna solo el @key (id) — el Router hace la resolución
     * del resto de campos contra el subgraph Products.
     */
    @DgsData(parentType = "Review", field = "product")
    public ProductRef getProduct(DgsDataFetchingEnvironment dfe) {
        Review review = dfe.getSource();
        return new ProductRef(review.productId());
    }

    @DgsEntityFetcher(name = "Review")
    public Review resolveReviewEntity(Map<String, Object> values) {
        String id = (String) values.get("id");
        return findById(id).orElse(null);
    }

    private Optional<Review> findById(String id) {
        return REVIEWS.stream()
                .filter(r -> r.id().equals(id))
                .findFirst();
    }
}