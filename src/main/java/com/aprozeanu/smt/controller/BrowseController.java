package com.aprozeanu.smt.controller;

import com.aprozeanu.smt.model.store.Market;
import com.aprozeanu.smt.model.store.Store;
import com.aprozeanu.smt.model.store.StoreSection;
import com.aprozeanu.smt.model.store.StoreSectionEntry;
import com.aprozeanu.smt.repository.MarketRepository;
import com.aprozeanu.smt.service.BrowseStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/browse")
public class BrowseController {

    @Autowired
    BrowseStoreService browseStoreService;
    @Autowired
    private MarketRepository marketRepository;

    @GetMapping("/markets")
    public List<Market> getAllMarkets() {
        return browseStoreService.getAllMarkets();
    }

    @GetMapping("/markets/{market}")
    public Market getMarket(@PathVariable("market") String market) {
        return marketRepository.getFirstByName(market);
    }

    @GetMapping("/markets/{market}/stores")
    public List<Store> getStoresByMarket(@PathVariable("market") String market) {
        return browseStoreService.getAllStoresByMarket(market);
    }

    @GetMapping("/stores")
    public List<Store> getStores() {
        return browseStoreService.getAllStores();
    }

    @GetMapping("/stores/{store}")
    public Store getStore(@PathVariable("store") String store) {
        return browseStoreService.getStoreByName(store);
    }

    @GetMapping("/stores/{store}/sections")
    public List<StoreSection> getSections(@PathVariable("store") String store) {
        return browseStoreService.getAllStoreSections(store);
    }

    @GetMapping("/sections/{sectionId}")
    public StoreSection getStoreSection(@PathVariable("sectionId") Long sectionId) {
        return browseStoreService.getStoreSection(sectionId);
    }

    @GetMapping("/sections/{sectionId}/entries")
    public Page<StoreSectionEntry> getStoreSectionEntries(@PathVariable("sectionId") Long sectionId, @NonNull Pageable pageable) {
        return browseStoreService.getStoreSectionEntries(sectionId, pageable);
    }
}
