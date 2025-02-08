package com.aprozeanu.smt.service;

import com.aprozeanu.smt.model.product.Product;
import com.aprozeanu.smt.model.store.Market;
import com.aprozeanu.smt.model.store.Store;
import com.aprozeanu.smt.model.store.StoreSection;
import com.aprozeanu.smt.model.store.StoreSectionEntry;
import com.aprozeanu.smt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrowseStoreService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StoreSectionRepository storeSectionRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    StoreSectionEntryRepository storeSectionEntryRepository;

    public List<Store> getAllStoresByMarket(String market) {
        return storeRepository.getAllByMarketName(market);
    }

    public List<StoreSection> getAllStoreSections(String store) {
        return storeSectionRepository.getAllByStoreName(store);
    }

    public List<Product> getAllProducts(String store) {
        return new ArrayList<>();
    }

    public List<Market> getAllMarkets() {
        return marketRepository.findAll();
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreByName(String store) {
        return storeRepository.getFirstByName(store);
    }

    public StoreSection getStoreSection(Long sectionId) {
        return storeSectionRepository.getFirstById(sectionId);
    }

    public Page<StoreSectionEntry> getStoreSectionEntries(Long sectionId, Pageable pageable) {
        return storeSectionEntryRepository.findStoreSectionEntryById(sectionId, pageable);
    }
}
