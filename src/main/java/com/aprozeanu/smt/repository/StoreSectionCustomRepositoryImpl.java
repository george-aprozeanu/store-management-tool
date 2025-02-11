package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.product.Product;
import com.aprozeanu.smt.model.store.StoreSection;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("storeSectionCustomRepositoryImpl")
public class StoreSectionCustomRepositoryImpl implements StoreSectionCustomRepository {

    private final EntityManager em;

    public StoreSectionCustomRepositoryImpl(JpaContext context) {
        this.em = context.getEntityManagerByManagedType(StoreSection.class);
    }

    public List<StoreSection> getStoreSectionsByProduct(Product product) {
        return em.createQuery("select sse.storeSection from StoreSectionEntry sse where sse.product = :product",
                StoreSection.class)
            .setParameter("product", product)
            .getResultList();
    }
}
