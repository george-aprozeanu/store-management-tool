package com.aprozeanu.smt.service.browse.dto;

import org.springframework.data.domain.Page;

public record StoreSectionEntriesResponse(Page<StoreSectionEntry> entries) {
    record StoreSectionEntry(Long entryId, Long productId, String productName, String priceBeforeTax,
                             String priceAfterTax) {
    }
}
