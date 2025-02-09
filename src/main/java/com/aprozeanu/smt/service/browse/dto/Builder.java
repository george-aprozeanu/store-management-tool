package com.aprozeanu.smt.service.browse.dto;


import com.aprozeanu.smt.model.store.StoreSection;
import com.aprozeanu.smt.model.store.StoreSectionEntry;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

public class Builder {

    private final static BigDecimal _100 = BigDecimal.valueOf(100);

    public static AllMarketsResponse allMarketsResponse(List<com.aprozeanu.smt.model.store.Market> dbMarkets) {
        return new AllMarketsResponse(dbMarkets.stream().map(Builder::market).toList());
    }

    static Market market(com.aprozeanu.smt.model.store.Market dbMarket) {
        return new Market(dbMarket.getName(), dbMarket.getCurrency().getSymbol());
    }

    public static AllStoresResponse allStoresResponse(List<com.aprozeanu.smt.model.store.Store> stores) {
        return new AllStoresResponse(stores.stream().map(Builder::store).toList());
    }

    public static MarketResponse marketResponse(com.aprozeanu.smt.model.store.Market dbMarket) {
        return new MarketResponse(market(dbMarket));
    }

    static Store store(com.aprozeanu.smt.model.store.Store store) {
        return new Store(store.getName(), store.getMarket().getName());
    }

    public static StoresByMarketResponse storesByMarketResponse(String marketName,
                                                                List<com.aprozeanu.smt.model.store.Store> stores) {
        return new StoresByMarketResponse(marketName, stores.stream().map(Builder::store).toList());
    }

    public static AllStoreSectionsResponse allStoreSectionsResponse(List<StoreSection> storeSections) {
        return new AllStoreSectionsResponse(storeSections.stream().map(
            dbStoreSection -> new AllStoreSectionsResponse.StoreSection(dbStoreSection.getId(),
                dbStoreSection.getName())).toList());
    }

    public static StoreSectionEntriesResponse storeSectionEntriesResponse(Page<StoreSectionEntry> storeSectionEntryById) {
        return new StoreSectionEntriesResponse(storeSectionEntryById.map(Builder::storeSectionEntry));
    }

    private static StoreSectionEntriesResponse.StoreSectionEntry storeSectionEntry(StoreSectionEntry storeSectionEntry) {
        return new StoreSectionEntriesResponse.StoreSectionEntry(storeSectionEntry.getId(),
            storeSectionEntry.getProduct().getId(), storeSectionEntry.getProduct().getName(),
            Builder.priceBeforeTax(storeSectionEntry), Builder.priceAfterTax(storeSectionEntry));
    }

    private static NumberFormat getNumberFormat(com.aprozeanu.smt.model.price.Currency currency) {
        var numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(Currency.getInstance(currency.getSymbol()));
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat;
    }

    private static String priceBeforeTax(StoreSectionEntry storeSectionEntry) {
        var currency = storeSectionEntry.getSection().getStore().getMarket().getCurrency();
        var numberFormat = getNumberFormat(currency);
        var multiplier = BigDecimal.valueOf(currency.getMultiplier());
        var value = BigDecimal.valueOf(storeSectionEntry.getPrice()).divide(multiplier, MathContext.DECIMAL32);
        return numberFormat.format(value);
    }

    private static String priceAfterTax(StoreSectionEntry storeSectionEntry) {
        var currency = storeSectionEntry.getSection().getStore().getMarket().getCurrency();
        var numberFormat = getNumberFormat(currency);
        var multiplier = BigDecimal.valueOf(currency.getMultiplier());
        var taxPercent = BigDecimal.valueOf(storeSectionEntry.getTaxCategory().getPercent());
        var value = BigDecimal.valueOf(storeSectionEntry.getPrice()).divide(multiplier, MathContext.DECIMAL32).multiply(
            taxPercent.add(_100)).divide(_100, MathContext.DECIMAL32);
        return numberFormat.format(value);
    }

    public static StoreResponse storeResponse(com.aprozeanu.smt.model.store.Store store) {
        return new StoreResponse(store(store));
    }

    public static StoreSectionResponse storeSectionResponse(StoreSection storeSection) {
        return new StoreSectionResponse(
            new StoreSectionResponse.StoreSection(storeSection.getId(), storeSection.getName(),
                storeSection.getDescription()));
    }
}
