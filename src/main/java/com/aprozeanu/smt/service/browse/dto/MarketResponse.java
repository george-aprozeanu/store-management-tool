package com.aprozeanu.smt.service.browse.dto;

public record MarketResponse(Market market) {
    public static MarketResponse from(com.aprozeanu.smt.model.store.Market dbMarket) {
        return new MarketResponse(Market.from(dbMarket));
    }
}
