package com.aprozeanu.smt.service.browse;

import static com.aprozeanu.smt.service.browse.dto.Builder.allMarketsResponse;
import static com.aprozeanu.smt.service.browse.dto.Builder.allStoreSectionsResponse;
import static com.aprozeanu.smt.service.browse.dto.Builder.allStoresResponse;
import static com.aprozeanu.smt.service.browse.dto.Builder.marketResponse;
import static com.aprozeanu.smt.service.browse.dto.Builder.storeResponse;
import static com.aprozeanu.smt.service.browse.dto.Builder.storeSectionEntriesResponse;
import static com.aprozeanu.smt.service.browse.dto.Builder.storeSectionResponse;
import static com.aprozeanu.smt.service.browse.dto.Builder.storesByMarketResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aprozeanu.smt.repository.MarketRepository;
import com.aprozeanu.smt.repository.StoreRepository;
import com.aprozeanu.smt.repository.StoreSectionEntryRepository;
import com.aprozeanu.smt.repository.StoreSectionRepository;
import com.aprozeanu.smt.service.browse.dto.AllMarketsResponse;
import com.aprozeanu.smt.service.browse.dto.AllStoreSectionsResponse;
import com.aprozeanu.smt.service.browse.dto.AllStoresResponse;
import com.aprozeanu.smt.service.browse.dto.MarketResponse;
import com.aprozeanu.smt.service.browse.dto.StoreResponse;
import com.aprozeanu.smt.service.browse.dto.StoreSectionEntriesResponse;
import com.aprozeanu.smt.service.browse.dto.StoreSectionResponse;
import com.aprozeanu.smt.service.browse.dto.StoresByMarketResponse;

@Service
public class BrowseStoreService {


    final
    StoreSectionRepository storeSectionRepository;

    final
    StoreRepository storeRepository;

    final
    MarketRepository marketRepository;

    final
    StoreSectionEntryRepository storeSectionEntryRepository;

    public BrowseStoreService(StoreSectionRepository storeSectionRepository, StoreRepository storeRepository, MarketRepository marketRepository, StoreSectionEntryRepository storeSectionEntryRepository) {
        this.storeSectionRepository = storeSectionRepository;
        this.storeRepository = storeRepository;
        this.marketRepository = marketRepository;
        this.storeSectionEntryRepository = storeSectionEntryRepository;
    }

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
