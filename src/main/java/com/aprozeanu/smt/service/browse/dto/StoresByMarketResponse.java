package com.aprozeanu.smt.service.browse.dto;

import java.util.List;

public record StoresByMarketResponse(String market, List<Store> stores) {
    public static StoresByMarketResponse from(String marketName,
                                              List<com.aprozeanu.smt.model.store.Store> dbStores) {
        var stores = dbStores.stream().map(Store::from).toList();
        return new StoresByMarketResponse(marketName, stores);
    }
}
