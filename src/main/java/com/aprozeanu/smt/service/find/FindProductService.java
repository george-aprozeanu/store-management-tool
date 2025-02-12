package com.aprozeanu.smt.service.find;

import com.aprozeanu.smt.repository.ProductCustomRepositoryImpl;
import com.aprozeanu.smt.service.find.dto.SearchProductsResponse;
import org.springframework.stereotype.Service;

import static com.aprozeanu.smt.service.find.dto.SearchProductsResponse.from;

@Service
public class FindProductService {

    final ProductCustomRepositoryImpl productSearchRepository;

    public FindProductService(ProductCustomRepositoryImpl productSearchRepository) {
        this.productSearchRepository = productSearchRepository;
    }

    public SearchProductsResponse searchProducts(String search, int limit) {
        var products = this.productSearchRepository.search(search, limit);
        return from(products);
    }
}
