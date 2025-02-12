package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.product.Product;
import jakarta.persistence.EntityManager;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Component("productCustomRepositoryImpl")
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final EntityManager em;

    public ProductCustomRepositoryImpl(JpaContext context) {
        this.em = context.getEntityManagerByManagedType(Product.class);
    }

    @Transactional
    public void startIndex() {
        SearchSession searchSession = Search.session(em);
        searchSession.massIndexer().start();
    }

    private List<Product> getSearchResult(String text, String[] fields, int limit) {
        SearchSession searchSession = Search.session(em);
        return searchSession
            .search(Product.class)
            .where(f -> f.match().fields(fields).matching(text).fuzzy(2))
            .fetchHits(limit);
    }

    public List<Product> search(String text, int limit) {
        return getSearchResult(text,
            new String[]{"name", "description"},
            limit);
    }

}
