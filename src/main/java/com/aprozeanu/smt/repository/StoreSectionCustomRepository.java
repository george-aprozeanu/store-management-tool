package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.product.Product;
import com.aprozeanu.smt.model.store.StoreSection;

import java.util.List;

public interface StoreSectionCustomRepository {
    List<StoreSection> getStoreSectionsByProduct(Product product);
}
