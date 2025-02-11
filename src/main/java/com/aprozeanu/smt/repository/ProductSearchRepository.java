package com.aprozeanu.smt.repository;

import com.aprozeanu.smt.model.product.Product;
import jakarta.persistence.EntityManager;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductSearchRepository extends SimpleJpaRepository<Product, Long> {

    private final EntityManager em;

    public ProductSearchRepository(EntityManager em) {
        super(Product.class, em);
        this.em = em;
    }

    @Transactional
    public void startIndex() {
        SearchSession searchSession = Search.session(em);
        searchSession.massIndexer().start();
    }

    private List<Product> getSearchResult(String text, String[] fields, int limit) {
        SearchSession searchSession = Search.session(em);
        return searchSession
            .search(getDomainClass())
            .where(f -> f.match().fields(fields).matching(text).fuzzy(2))
            .fetchHits(limit);
    }

    public List<Product> search(String text, int limit) {
        return getSearchResult(text, new String[]{"name", "description"}, limit);
    }

}
