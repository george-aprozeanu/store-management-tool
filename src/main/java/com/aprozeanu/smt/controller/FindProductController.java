package com.aprozeanu.smt.controller;

import com.aprozeanu.smt.service.find.FindProductService;
import com.aprozeanu.smt.service.find.dto.SearchProductsResponse;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/find")
public class FindProductController {

    final FindProductService service;

    public FindProductController(FindProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public SearchProductsResponse getProductsByName(@RequestParam("search") String search, @RequestParam(value =
        "limit", defaultValue = "20") @Min(1) int limit) {
        return service.searchProducts(search, limit);
    }
}
