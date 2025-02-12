package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.product.Product;

import java.util.List;

public interface ProductCustomRepository {
    void startIndex();

    List<Product> search(String text, int limit);
}
