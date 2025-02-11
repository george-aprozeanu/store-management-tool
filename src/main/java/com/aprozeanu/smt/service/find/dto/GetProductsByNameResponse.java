package com.aprozeanu.smt.service.find.dto;

public record GetProductsByNameResponse(org.springframework.data.domain.Page<Product> products) {
}
