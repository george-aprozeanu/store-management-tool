package com.aprozeanu.smt.controller;

import com.aprozeanu.smt.service.find.FindProductService;
import com.aprozeanu.smt.service.find.dto.AllProductsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/find")
public class FindProductController {

    final FindProductService findProductService;

    public FindProductController(FindProductService findProductService) {
        this.findProductService = findProductService;
    }

    @GetMapping("/products")
    public AllProductsResponse getProducts(Pageable pageable) {
        return findProductService.getAllProducts(pageable);
    }
}
