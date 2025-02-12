package com.aprozeanu.smt.service.browse;

import com.aprozeanu.smt.model.store.Market;
import com.aprozeanu.smt.model.store.StoreSection;
import com.aprozeanu.smt.model.store.StoreSectionEntry;
import com.aprozeanu.smt.repository.*;
import com.aprozeanu.smt.service.browse.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.aprozeanu.smt.service.browse.dto.Builder.*;

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

    public Optional<StoreResponse> getStoreByName(String store) {
        var maybeStore = storeRepository.getFirstByName(store);
        return maybeStore.map(Builder::storeResponse);
    }

    public Optional<StoreSectionResponse> getStoreSection(Long sectionId) {
        Optional<StoreSection> maybeStoreSection = storeSectionRepository.getFirstById(sectionId);
        return maybeStoreSection.map(Builder::storeSectionResponse);
    }

    public StoreSectionEntriesResponse getStoreSectionEntries(Long sectionId, Pageable pageable) {
        Page<StoreSectionEntry> storeSectionEntries = storeSectionEntryRepository.findStoreSectionEntryById(
            sectionId,
            pageable);
        return storeSectionEntriesResponse(storeSectionEntries);
    }

    public Optional<MarketResponse> getMarket(String name) {
        var maybeMarket = marketRepository.getFirstByName(name);
        return maybeMarket.map(Builder::marketResponse);
    }

    public Optional<ProductResponse> getProduct(Long productId) {
        var maybeProduct = this.productRepository.getFirstById(productId);
        return maybeProduct.map(product -> {
            var storeSections = this.storeSectionCustomRepository.getStoreSectionsByProduct(product);
            return productResponse(product, storeSections);
        });
    }
}
