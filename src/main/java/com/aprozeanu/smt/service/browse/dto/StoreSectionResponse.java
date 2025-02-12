package com.aprozeanu.smt.service.browse.dto;

public record StoreSectionResponse(StoreSection storeSection) {
    public static StoreSectionResponse from(com.aprozeanu.smt.model.store.StoreSection storeSection) {
        var id = storeSection.getId();
        var name = storeSection.getName();
        var description = storeSection.getDescription();
        return new StoreSectionResponse(new StoreSection(id, name, description));
    }

    record StoreSection(Long id, String name, String description) {
    }
}
