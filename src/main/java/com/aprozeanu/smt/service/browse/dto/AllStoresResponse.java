package com.aprozeanu.smt.service.browse.dto;

import java.util.List;

public record AllStoresResponse(List<Store> stores) {

    public static AllStoresResponse from(List<com.aprozeanu.smt.model.store.Store> dbStores) {
        var stores = dbStores.stream().map(Store::from).toList();
        return new AllStoresResponse(stores);
    }
}
