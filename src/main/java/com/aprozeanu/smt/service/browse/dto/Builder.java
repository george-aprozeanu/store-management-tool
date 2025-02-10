package com.aprozeanu.smt.service.browse.dto;


import com.aprozeanu.smt.model.price.Pricing;
import com.aprozeanu.smt.model.store.StoreSection;
import com.aprozeanu.smt.model.store.StoreSectionEntry;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

public class Builder {

    public static AllMarketsResponse allMarketsResponse(List<com.aprozeanu.smt.model.store.Market> dbMarkets) {
        List<Market> markets = dbMarkets.stream().map(Builder::market).toList();
        return new AllMarketsResponse(markets);
    }

    static Market market(com.aprozeanu.smt.model.store.Market dbMarket) {
        String name = dbMarket.getName();
        String symbol = dbMarket.getCurrency().getSymbol();
        return new Market(name, symbol);
    }

    public static AllStoresResponse allStoresResponse(List<com.aprozeanu.smt.model.store.Store> dbStores) {
        List<Store> stores = dbStores.stream().map(Builder::store).toList();
        return new AllStoresResponse(stores);
    }

    public static MarketResponse marketResponse(com.aprozeanu.smt.model.store.Market dbMarket) {
        return new MarketResponse(market(dbMarket));
    }

    static Store store(com.aprozeanu.smt.model.store.Store store) {
        return new Store(store.getName(), store.getMarket().getName());
    }

    public static StoresByMarketResponse storesByMarketResponse(String marketName,
                                                                List<com.aprozeanu.smt.model.store.Store> dbStores) {
        List<Store> stores = dbStores.stream().map(Builder::store).toList();
        return new StoresByMarketResponse(marketName, stores);
    }

    public static AllStoreSectionsResponse allStoreSectionsResponse(List<StoreSection> dbStoreSections) {
        List<AllStoreSectionsResponse.StoreSection> storeSections = dbStoreSections.stream().map(dbStoreSection -> {
            Long id = dbStoreSection.getId();
            String name = dbStoreSection.getName();
            return new AllStoreSectionsResponse.StoreSection(id, name);
        }).toList();
        return new AllStoreSectionsResponse(storeSections);
    }

    public static StoreSectionEntriesResponse storeSectionEntriesResponse(Page<StoreSectionEntry> storeSectionEntryById) {
        return new StoreSectionEntriesResponse(storeSectionEntryById.map(Builder::storeSectionEntry));
    }

    private static StoreSectionEntriesResponse.StoreSectionEntry storeSectionEntry(StoreSectionEntry storeSectionEntry) {
        Long id = storeSectionEntry.getId();
        Long productId = storeSectionEntry.getProduct().getId();
        String name = storeSectionEntry.getProduct().getName();
        String priceBeforeTax = Builder.formatPriceForEntry(Pricing.getEntryPrice(storeSectionEntry),
            storeSectionEntry);
        String priceAfterTax = Builder.formatPriceForEntry(Pricing.getEntryPriceWithTax(storeSectionEntry),
            storeSectionEntry);
        return new StoreSectionEntriesResponse.StoreSectionEntry(id, productId, name, priceBeforeTax, priceAfterTax);
    }

    private static NumberFormat getNumberFormat(com.aprozeanu.smt.model.price.Currency currency) {
        var numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(Currency.getInstance(currency.getSymbol()));
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat;
    }

    private static String formatPriceForEntry(BigDecimal value, StoreSectionEntry storeSectionEntry) {
        com.aprozeanu.smt.model.price.Currency currency =
            storeSectionEntry.getSection().getStore().getMarket().getCurrency();
        return getNumberFormat(currency).format(value);
    }

    public static StoreResponse storeResponse(com.aprozeanu.smt.model.store.Store store) {
        return new StoreResponse(store(store));
    }

    public static StoreSectionResponse storeSectionResponse(StoreSection storeSection) {
        Long id = storeSection.getId();
        String name = storeSection.getName();
        String description = storeSection.getDescription();
        return new StoreSectionResponse(new StoreSectionResponse.StoreSection(id, name, description));
    }
}
