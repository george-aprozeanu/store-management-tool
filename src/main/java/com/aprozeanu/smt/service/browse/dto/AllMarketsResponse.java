package com.aprozeanu.smt.service.browse.dto;

import java.util.List;

public record AllMarketsResponse(List<Market> markets) {
    public static AllMarketsResponse from(List<com.aprozeanu.smt.model.store.Market> dbMarkets) {
        var markets = dbMarkets.stream().map(Market::from).toList();
        return new AllMarketsResponse(markets);
    }
}
