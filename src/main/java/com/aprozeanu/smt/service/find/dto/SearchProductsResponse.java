package com.aprozeanu.smt.service.find.dto;

import java.util.List;

public record SearchProductsResponse(List<SearchProductsResponse.Product> products) {
    static Product from(com.aprozeanu.smt.model.product.Product product) {
        return new Product(product.getId(), product.getName(), product.getDescription());
    }

    public static SearchProductsResponse from(List<com.aprozeanu.smt.model.product.Product> dbProducts) {
        var products = dbProducts.stream().map(SearchProductsResponse::from).toList();
        return new SearchProductsResponse(products);
    }

    public record Product(Long id, String name, String description) {
    }
}
