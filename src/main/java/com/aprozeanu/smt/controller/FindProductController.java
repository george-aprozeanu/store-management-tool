package com.aprozeanu.smt.controller;

import com.aprozeanu.smt.service.find.FindProductService;
import com.aprozeanu.smt.service.find.dto.SearchProductsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/find")
public class FindProductController {

    final FindProductService findProductService;

    public FindProductController(FindProductService findProductService) {
        this.findProductService = findProductService;
    }

    @GetMapping("/products")
    public SearchProductsResponse getProductsByName(@RequestParam("search") String search,
                                                    @RequestParam(value = "limit", defaultValue = "20") int limit) {
        return findProductService.searchProducts(search, limit);
    }
}
