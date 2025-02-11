package com.aprozeanu.smt.service.browse.dto;

public record ProductResponse(ProductResponse.Product product,
                              java.util.List<ProductResponse.StoreSection> storeSections) {
    public record StoreSection(Long id, String name) {
    }

    public record Product(Long id, String name, String description) {

    }
}
