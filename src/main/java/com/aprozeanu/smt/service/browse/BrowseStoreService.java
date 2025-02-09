package com.aprozeanu.smt.service.browse;

import com.aprozeanu.smt.repository.*;
import com.aprozeanu.smt.service.browse.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.aprozeanu.smt.service.browse.dto.Builder.*;

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

    public StoresByMarketResponse getAllStoresByMarket(String market) {
        return storesByMarketResponse(market, storeRepository.getAllByMarketName(market));
    }

    public AllStoreSectionsResponse getAllStoreSections(String store) {
        return allStoreSectionsResponse(storeSectionRepository.getAllByStoreName(store));
    }

    public AllMarketsResponse getAllMarkets() {
        return allMarketsResponse(marketRepository.findAll());
    }

    public AllStoresResponse getAllStores() {
        return allStoresResponse(storeRepository.findAll());
    }

    public StoreResponse getStoreByName(String store) {
        return storeResponse(storeRepository.getFirstByName(store));
    }

    public StoreSectionResponse getStoreSection(Long sectionId) {
        return storeSectionResponse(storeSectionRepository.getFirstById(sectionId));
    }

    public StoreSectionEntriesResponse getStoreSectionEntries(Long sectionId, Pageable pageable) {
        return storeSectionEntriesResponse(storeSectionEntryRepository.findStoreSectionEntryById(sectionId, pageable));
    }

    public MarketResponse getMarket(String name) {
        return marketResponse(marketRepository.getFirstByName(name));
    }
}
