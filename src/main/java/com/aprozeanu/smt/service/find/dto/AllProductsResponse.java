package com.aprozeanu.smt.service.find.dto;

import java.util.List;

public record AllProductsResponse(List<Product> product, org.springframework.data.domain.Pageable pageable) {
}
