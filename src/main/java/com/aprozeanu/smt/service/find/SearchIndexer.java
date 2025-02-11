package com.aprozeanu.smt.service.find;

import com.aprozeanu.smt.repository.ProductSearchRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SearchIndexer {

    private final ProductSearchRepository productSearchRepository;

    public SearchIndexer(ProductSearchRepository productSearchRepository) {
        this.productSearchRepository = productSearchRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    @Transactional
    public void onApplicationEvent() {
        productSearchRepository.startIndex();
    }
}
