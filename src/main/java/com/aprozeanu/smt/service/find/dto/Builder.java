package com.aprozeanu.smt.service.find.dto;

import java.util.List;

public class Builder {

    private static SearchProductsResponse.Product searchProduct(com.aprozeanu.smt.model.product.Product product) {
        return new SearchProductsResponse.Product(product.getId(), product.getName(), product.getDescription());
    }

    public static SearchProductsResponse searchProductsResponse(List<com.aprozeanu.smt.model.product.Product> dbProducts) {
        var products = dbProducts.stream().map(Builder::searchProduct).toList();
        return new SearchProductsResponse(products);
    }
}
