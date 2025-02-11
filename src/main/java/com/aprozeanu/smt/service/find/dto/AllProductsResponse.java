package com.aprozeanu.smt.service.find.dto;

public record AllProductsResponse(org.springframework.data.domain.Page<Product> product,
                                  org.springframework.data.domain.Pageable pageable) {
}
