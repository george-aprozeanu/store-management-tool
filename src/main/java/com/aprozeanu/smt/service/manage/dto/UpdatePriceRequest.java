package com.aprozeanu.smt.service.manage.dto;

import jakarta.validation.constraints.Min;

public record UpdatePriceRequest(Long productId, Long storeSectionId,
                                 @Min(1) Integer price) implements ManageStoreRequest<UpdatePriceRequest.UpdatePriceResponse> {
    public record UpdatePriceResponse(Long storeSectionEntryId, Integer price) {
    }
}
