package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    Optional<Product> getFirstById(Long id);
}
