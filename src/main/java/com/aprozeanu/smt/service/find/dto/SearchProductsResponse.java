package com.aprozeanu.smt.service.find.dto;

import java.util.List;

public record SearchProductsResponse(List<SearchProductsResponse.Product> products) {
    public record Product(Long id, String name, String description) {
    }
}
