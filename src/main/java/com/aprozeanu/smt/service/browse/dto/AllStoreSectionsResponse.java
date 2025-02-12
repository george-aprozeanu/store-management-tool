package com.aprozeanu.smt.service.browse.dto;

import java.util.List;

public record AllStoreSectionsResponse(List<StoreSection> storeSections) {
    public static AllStoreSectionsResponse from(List<com.aprozeanu.smt.model.store.StoreSection> dbStoreSections) {
        var storeSections = dbStoreSections.stream().map(dbStoreSection -> {
            var id = dbStoreSection.getId();
            var name = dbStoreSection.getName();
            return new AllStoreSectionsResponse.StoreSection(id, name);
        }).toList();
        return new AllStoreSectionsResponse(storeSections);
    }

    record StoreSection(Long id, String Name) {
    }
}
