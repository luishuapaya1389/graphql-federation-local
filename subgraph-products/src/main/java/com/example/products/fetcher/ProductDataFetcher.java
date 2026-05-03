package com.example.products.fetcher;

import com.example.products.model.Product;
import com.netflix.graphql.dgs.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@DgsComponent
public class ProductDataFetcher {

    // Dataset en memoria — en fases futuras será reemplazado por un repositorio
    // real
    private static final List<Product> PRODUCTS = List.of(
            new Product("1", "Laptop Pro M3", "Laptop de alto rendimiento", 1299.99, "Electronics", true),
            new Product("2", "Wireless Mouse", "Mouse ergonómico inalámbrico", 49.99, "Electronics", true),
            new Product("3", "Standing Desk", "Escritorio ajustable", 599.99, "Furniture", false),
            new Product("4", "Noise Headphones", "Cancelación activa de ruido", 349.99, "Electronics", true));

    @DgsQuery
    public List<Product> products() {
        return PRODUCTS;
    }

    @DgsQuery
    public Product product(@InputArgument String id) {
        return findById(id).orElse(null);
    }

    /**
     * Resuelve entidades Product referenciadas desde otros subgraphs.
     * Apollo Router llama a _entities con los @key fields de cada Product.
     */
    @DgsEntityFetcher(name = "Product")
    public Product resolveProductEntity(Map<String, Object> values) {
        String id = (String) values.get("id");
        return findById(id).orElse(null);
    }

    private Optional<Product> findById(String id) {
        return PRODUCTS.stream()
                .filter(p -> p.id().equals(id))
                .findFirst();
    }
}