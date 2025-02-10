package com.aprozeanu.smt.controller;

import com.aprozeanu.smt.service.browse.BrowseStoreService;
import com.aprozeanu.smt.service.browse.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/browse")
public class BrowseController {

    final
    BrowseStoreService browseStoreService;

    public BrowseController(BrowseStoreService browseStoreService) {
        this.browseStoreService = browseStoreService;
    }

    @GetMapping("/markets")
    public AllMarketsResponse getAllMarkets() {
        return browseStoreService.getAllMarkets();
    }

    @GetMapping("/markets/{market}")
    public MarketResponse getMarket(@PathVariable("market") String market) {
        return browseStoreService.getMarket(market);
    }

    @GetMapping("/markets/{market}/stores")
    public StoresByMarketResponse getStoresByMarket(@PathVariable("market") String market) {
        return browseStoreService.getAllStoresByMarket(market);
    }

    @GetMapping("/stores")
    public AllStoresResponse getStores() {
        return browseStoreService.getAllStores();
    }

    @GetMapping("/stores/{store}")
    public StoreResponse getStore(@PathVariable("store") String store) {
        return browseStoreService.getStoreByName(store);
    }

    @GetMapping("/stores/{store}/sections")
    public AllStoreSectionsResponse getSections(@PathVariable("store") String store) {
        return browseStoreService.getAllStoreSections(store);
    }

    @GetMapping("/sections/{sectionId}")
    public StoreSectionResponse getStoreSection(@PathVariable("sectionId") Long sectionId) {
        return browseStoreService.getStoreSection(sectionId);
    }

    @GetMapping("/sections/{sectionId}/entries")
    public StoreSectionEntriesResponse getStoreSectionEntries(@PathVariable("sectionId") Long sectionId,
                                                              @NonNull Pageable pageable) {
        return browseStoreService.getStoreSectionEntries(sectionId, pageable);
    }
}
