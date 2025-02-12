package com.aprozeanu.smt.service.browse.dto;

record Store(String name, String market) {
    static Store from(com.aprozeanu.smt.model.store.Store store) {
        return new Store(store.getName(), store.getMarket().getName());
    }
}
