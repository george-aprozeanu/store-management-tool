package com.aprozeanu.smt.service.find;

import com.aprozeanu.smt.model.product.Product;
import com.aprozeanu.smt.repository.ProductRepository;
import com.aprozeanu.smt.repository.ProductSearchRepository;
import com.aprozeanu.smt.service.find.dto.AllProductsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.aprozeanu.smt.service.find.dto.Builder.allProductsResponse;

import java.util.List;

@Service
public class FindProductService {

    final ProductSearchRepository productSearchRepository;
    final ProductRepository productRepository;

    public FindProductService(ProductSearchRepository productSearchRepository, ProductRepository productRepository) {
        this.productSearchRepository = productSearchRepository;
        this.productRepository = productRepository;
    }

    public AllProductsResponse getAllProducts(Pageable pageable) {
        var products = this.productRepository.findAll();
        return allProductsResponse(products, pageable);
    }

    private Object products(List<Product> products) {
        return null;
    }
}
