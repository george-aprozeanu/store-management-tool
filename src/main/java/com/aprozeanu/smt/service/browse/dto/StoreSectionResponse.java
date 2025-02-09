package com.aprozeanu.smt.service.browse.dto;

public record StoreSectionResponse(StoreSection storeSection) {
    record StoreSection(Long id, String name, String description) {
    }
}
