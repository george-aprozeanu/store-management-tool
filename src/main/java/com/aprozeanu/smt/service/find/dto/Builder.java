package com.aprozeanu.smt.service.find.dto;

import org.springframework.data.domain.Pageable;

import java.util.List;

public class Builder {
    static Product product(com.aprozeanu.smt.model.product.Product dbProduct) {
        return new Product(dbProduct.getName());
    }

    public static List<Product> products(List<com.aprozeanu.smt.model.product.Product> dbProducts) {
        return dbProducts.stream()
            .map(Builder::product)
            .toList();
    }

    public static AllProductsResponse allProductsResponse(List<com.aprozeanu.smt.model.product.Product> products, Pageable pageable) {
        return new AllProductsResponse(products(products), pageable);
    }
}
