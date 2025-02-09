package com.aprozeanu.smt.service.browse.dto;

import java.util.List;

public record StoresByMarketResponse(String market, List<Store> stores) {
}
