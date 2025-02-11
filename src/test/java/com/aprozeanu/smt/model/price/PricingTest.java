package com.aprozeanu.smt.model.price;

import com.aprozeanu.smt.model.store.Market;
import com.aprozeanu.smt.model.store.Store;
import com.aprozeanu.smt.model.store.StoreSection;
import com.aprozeanu.smt.model.store.StoreSectionEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingTest {

    @Test
    public void testGetEntryPrice() {
        Currency currency = new Currency();
        currency.setMultiplier(1);

        Store store = new Store();
        Market market = new Market();
        market.setCurrency(currency);
        store.setMarket(market);

        StoreSection storeSection = new StoreSection();
        storeSection.setStore(store);

        StoreSectionEntry storeSectionEntry = new StoreSectionEntry();
        storeSectionEntry.setStoreSection(storeSection);
        storeSectionEntry.setPrice(100);

        BigDecimal expectedPrice = BigDecimal.valueOf(100);
        BigDecimal actualPrice = Pricing.getEntryPrice(storeSectionEntry);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testGetEntryPriceWithTax() {
        Currency currency = new Currency();
        currency.setMultiplier(1);

        Store store = new Store();
        Market market = new Market();
        market.setCurrency(currency);
        store.setMarket(market);

        StoreSection storeSection = new StoreSection();
        storeSection.setStore(store);

        StoreSectionEntry storeSectionEntry = new StoreSectionEntry();
        storeSectionEntry.setStoreSection(storeSection);
        storeSectionEntry.setPrice(100);

        TaxCategory taxCategory = new TaxCategory();
        taxCategory.setPercent(10);
        storeSectionEntry.setTaxCategory(taxCategory);

        BigDecimal expectedPriceWithTax = BigDecimal.valueOf(110);
        BigDecimal actualPriceWithTax = Pricing.getEntryPriceWithTax(storeSectionEntry);

        assertEquals(expectedPriceWithTax, actualPriceWithTax);
    }
}
