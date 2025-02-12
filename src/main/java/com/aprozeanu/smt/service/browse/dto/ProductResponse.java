package com.aprozeanu.smt.service.browse.dto;

import java.util.List;

public record ProductResponse(ProductResponse.Product product,
                              java.util.List<ProductResponse.StoreSection> storeSections) {
    public static ProductResponse from(com.aprozeanu.smt.model.product.Product dbProduct,
                                       List<com.aprozeanu.smt.model.store.StoreSection> dbStoreSections) {
        var storeSections = dbStoreSections.stream().map(dbStoreSection -> {
            var id = dbStoreSection.getId();
            var name = dbStoreSection.getName();
            return new StoreSection(id, name);
        }).toList();
        var product = new Product(dbProduct.getId(), dbProduct.getName(),
            dbProduct.getDescription());
        return new ProductResponse(product, storeSections);
    }

    public record StoreSection(Long id, String name) {
    }

    public record Product(Long id, String name, String description) {

    }
}
