package com.example.reviews.model;

public record Review(
        String id,
        String body,
        Integer rating,
        String author,
        String productId // FK interna — no expuesta en el schema
) {
}