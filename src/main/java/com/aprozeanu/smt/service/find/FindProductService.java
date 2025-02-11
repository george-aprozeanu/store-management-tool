package com.aprozeanu.smt.service.find;

import com.aprozeanu.smt.repository.ProductRepository;
import com.aprozeanu.smt.repository.ProductSearchRepository;
import com.aprozeanu.smt.service.find.dto.GetProductsByNameResponse;
import com.aprozeanu.smt.service.find.dto.SearchProductsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.aprozeanu.smt.service.find.dto.Builder.productsByNameResponse;
import static com.aprozeanu.smt.service.find.dto.Builder.searchProductsResponse;

@Service
public class FindProductService {

    final ProductSearchRepository productSearchRepository;
    final ProductRepository productRepository;

    public FindProductService(ProductSearchRepository productSearchRepository, ProductRepository productRepository) {
        this.productSearchRepository = productSearchRepository;
        this.productRepository = productRepository;
    }

    public GetProductsByNameResponse getAllProductsByName(String name, Pageable pageable) {
        var products = this.productRepository.findAllByNameContaining(name, pageable);
        return productsByNameResponse(products);
    }

    public SearchProductsResponse searchProducts(String search, int limit) {
        var products = this.productSearchRepository.search(search, limit);
        return searchProductsResponse(products);
    }
}
