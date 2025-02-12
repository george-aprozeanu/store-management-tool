package com.aprozeanu.smt.service.browse.dto;

public record StoreResponse(Store store) {
    public static StoreResponse from(com.aprozeanu.smt.model.store.Store store) {
        return new StoreResponse(Store.from(store));
    }
}
