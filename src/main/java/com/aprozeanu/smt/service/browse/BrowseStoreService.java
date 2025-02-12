package com.aprozeanu.smt.service.browse;

import com.aprozeanu.smt.model.store.StoreSectionEntry;
import com.aprozeanu.smt.repository.*;
import com.aprozeanu.smt.service.browse.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrowseStoreService {

    final ProductRepository productRepository;

    final StoreSectionRepository storeSectionRepository;

    final StoreRepository storeRepository;

    final MarketRepository marketRepository;

    final StoreSectionEntryRepository storeSectionEntryRepository;

    final StoreSectionCustomRepository storeSectionCustomRepository;

    public BrowseStoreService(ProductRepository productRepository, StoreSectionRepository storeSectionRepository,
                              StoreRepository storeRepository, MarketRepository marketRepository,
                              StoreSectionEntryRepository storeSectionEntryRepository,
                              StoreSectionCustomRepository storeSectionCustomRepository) {
        this.productRepository = productRepository;
        this.storeSectionRepository = storeSectionRepository;
        this.storeRepository = storeRepository;
        this.marketRepository = marketRepository;
        this.storeSectionEntryRepository = storeSectionEntryRepository;
        this.storeSectionCustomRepository = storeSectionCustomRepository;
    }

    public StoresByMarketResponse getAllStoresByMarket(String market) {
        return StoresByMarketResponse.from(market, storeRepository.getAllByMarketName(market));
    }

    public AllStoreSectionsResponse getAllStoreSections(String store) {
        return AllStoreSectionsResponse.from(storeSectionRepository.getAllByStoreName(store));
    }

    public AllMarketsResponse getAllMarkets() {
        return AllMarketsResponse.from(marketRepository.findAll());
    }

    public AllStoresResponse getAllStores() {
        return AllStoresResponse.from(storeRepository.findAll());
    }

    public Optional<StoreResponse> getStoreByName(String store) {
        return storeRepository.getFirstByName(store).map(StoreResponse::from);
    }

    public Optional<StoreSectionResponse> getStoreSection(Long sectionId) {
        return storeSectionRepository.getFirstById(sectionId).map(StoreSectionResponse::from);
    }

    public StoreSectionEntriesResponse getStoreSectionEntries(Long sectionId, Pageable pageable) {
        Page<StoreSectionEntry> storeSectionEntries = storeSectionEntryRepository.findStoreSectionEntryById(
            sectionId,
            pageable);
        return StoreSectionEntriesResponse.from(storeSectionEntries);
    }

    public Optional<MarketResponse> getMarket(String name) {
        return marketRepository.getFirstByName(name).map(MarketResponse::from);
    }

    public Optional<ProductResponse> getProduct(Long productId) {
        return this.productRepository.getFirstById(productId).map(product -> {
            var storeSections = this.storeSectionCustomRepository.getStoreSectionsByProduct(product);
            return ProductResponse.from(product, storeSections);
        });
    }
}
