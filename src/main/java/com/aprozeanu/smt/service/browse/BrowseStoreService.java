package com.aprozeanu.smt.service.browse;

import com.aprozeanu.smt.repository.*;
import com.aprozeanu.smt.service.browse.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.aprozeanu.smt.service.browse.dto.Builder.*;

@Service
public class BrowseStoreService {

    final ProductRepository productRepository;

    final
    StoreSectionRepository storeSectionRepository;

    final
    StoreRepository storeRepository;

    final
    MarketRepository marketRepository;

    final
    StoreSectionEntryRepository storeSectionEntryRepository;

    final
    StoreSectionCustomRepository storeSectionCustomRepository;

    public BrowseStoreService(ProductRepository productRepository, StoreSectionRepository storeSectionRepository,
                              StoreRepository storeRepository,
                              MarketRepository marketRepository,
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

    public ProductResponse getProduct(Long productId) {
        var maybeProduct = this.productRepository.getFirstById(productId);
        if (maybeProduct.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Store sections not found for product with ID: " + productId);
        }
        var product = maybeProduct.get(0);
        var storeSections = this.storeSectionCustomRepository.getStoreSectionsByProduct(product);
        return productResponse(product, storeSections);
    }
}
