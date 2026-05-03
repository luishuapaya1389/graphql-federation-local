package com.example.reviews.model;

/**
 * Referencia al tipo Product del subgraph Products.
 * Solo contiene el @key field (id) — el Router completa
 * los demás campos consultando al subgraph Products.
 */
public record ProductRef(String id) {
}