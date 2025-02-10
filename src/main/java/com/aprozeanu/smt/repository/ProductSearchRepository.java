package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.product.Product;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductSearchRepository extends SimpleJpaRepository<Product, Long> {
    public ProductSearchRepository(EntityManager em) {
        super(Product.class, em);
    }
}
