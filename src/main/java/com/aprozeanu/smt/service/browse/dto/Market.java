package com.aprozeanu.smt.service.browse.dto;

record Market(String name, String currency) {
    static Market from(com.aprozeanu.smt.model.store.Market dbMarket) {
        var name = dbMarket.getName();
        var symbol = dbMarket.getCurrency().getSymbol();
        return new Market(name, symbol);
    }
}
