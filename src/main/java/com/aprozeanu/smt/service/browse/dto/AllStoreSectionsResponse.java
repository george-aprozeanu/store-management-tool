package com.aprozeanu.smt.service.browse.dto;

import java.util.List;

public record AllStoreSectionsResponse(List<StoreSection> storeSections) {
    record StoreSection(Long id, String Name) {
    }
}
