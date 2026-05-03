package com.example.products.model;

public record Product(
        String id,
        String name,
        String description,
        Double price,
        String category,
        Boolean inStock) {
}