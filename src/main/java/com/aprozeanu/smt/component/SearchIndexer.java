package com.aprozeanu.smt.component;

import com.aprozeanu.smt.repository.ProductCustomRepositoryImpl;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SearchIndexer {

    private final ProductCustomRepositoryImpl productSearchRepository;

    public SearchIndexer(ProductCustomRepositoryImpl productSearchRepository) {
        this.productSearchRepository = productSearchRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void onApplicationEvent() {
        productSearchRepository.startIndex();
    }
}
