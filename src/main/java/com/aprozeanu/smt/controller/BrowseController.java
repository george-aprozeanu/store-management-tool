package com.aprozeanu.smt.controller;

import com.aprozeanu.smt.service.browse.BrowseStoreService;
import com.aprozeanu.smt.service.browse.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aprozeanu.smt.http.Response.foundOptional;

@RestController
@RequestMapping("/browse")
public class BrowseController {

    final BrowseStoreService service;

    public BrowseController(BrowseStoreService service) {
        this.service = service;
    }

    @GetMapping("/markets")
    public AllMarketsResponse getAllMarkets() {
        return service.getAllMarkets();
    }

    @GetMapping("/markets/{market}")
    public ResponseEntity<MarketResponse> getMarket(@PathVariable("market") String market) {
        return foundOptional(service.getMarket(market));
    }

    @GetMapping("/markets/{market}/stores")
    public StoresByMarketResponse getStoresByMarket(@PathVariable("market") String market) {
        return service.getAllStoresByMarket(market);
    }

    @GetMapping("/stores")
    public AllStoresResponse getStores() {
        return service.getAllStores();
    }

    @GetMapping("/stores/{store}")
    public ResponseEntity<StoreResponse> getStore(@PathVariable("store") String store) {
        return foundOptional(service.getStoreByName(store));
    }

    @GetMapping("/stores/{store}/sections")
    public AllStoreSectionsResponse getSections(@PathVariable("store") String store) {
        return service.getAllStoreSections(store);
    }

    @GetMapping("/sections/{sectionId}")
    public ResponseEntity<StoreSectionResponse> getStoreSection(@PathVariable("sectionId") Long sectionId) {
        var storeSection = service.getStoreSection(sectionId);
        return foundOptional(storeSection);
    }

    @GetMapping("/sections/{sectionId}/entries")
    public StoreSectionEntriesResponse getStoreSectionEntries(@PathVariable("sectionId") Long sectionId,
                                                              @NonNull Pageable pageable) {
        return service.getStoreSectionEntries(sectionId, pageable);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("productId") Long productId) {
        var product = service.getProduct(productId);
        return foundOptional(product);
    }
}
