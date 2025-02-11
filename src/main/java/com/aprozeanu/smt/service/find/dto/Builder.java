package com.aprozeanu.smt.service.find.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class Builder {
    static Product product(com.aprozeanu.smt.model.product.Product dbProduct) {
        return new Product(dbProduct.getName());
    }

    public static Page<Product> products(Page<com.aprozeanu.smt.model.product.Product> dbProducts) {
        return dbProducts.map(Builder::product);
    }

    public static List<SearchProductsResponse.Product> searchProducts(List<com.aprozeanu.smt.model.product.Product> dbProducts) {
        return dbProducts.stream().map(Builder::searchProduct).toList();
    }

    private static SearchProductsResponse.Product searchProduct(com.aprozeanu.smt.model.product.Product product) {
        return new SearchProductsResponse.Product(product.getId(), product.getName(), product.getDescription());
    }

    public static AllProductsResponse allProductsResponse(Page<com.aprozeanu.smt.model.product.Product> dbProducts,
                                                          Pageable pageable) {
        var products = products(dbProducts);
        return new AllProductsResponse(products, pageable);
    }

    public static GetProductsByNameResponse productsByNameResponse(Page<com.aprozeanu.smt.model.product.Product> dbProducts) {
        var products = products(dbProducts);
        return new GetProductsByNameResponse(products);
    }

    public static SearchProductsResponse searchProductsResponse(List<com.aprozeanu.smt.model.product.Product> dbProducts) {
        var products = searchProducts(dbProducts);
        return new SearchProductsResponse(products);
    }
}
