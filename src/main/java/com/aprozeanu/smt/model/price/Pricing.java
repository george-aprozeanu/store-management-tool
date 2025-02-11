package com.aprozeanu.smt.model.price;

import com.aprozeanu.smt.model.store.StoreSectionEntry;

import java.math.BigDecimal;
import java.math.MathContext;

public class Pricing {
    final static MathContext mathContext = MathContext.DECIMAL32;
    private final static BigDecimal _100 = BigDecimal.valueOf(100);

    public static BigDecimal getEntryPrice(StoreSectionEntry storeSectionEntry) {
        var currency = storeSectionEntry.getStoreSection().getStore().getMarket().getCurrency();
        var multiplier = BigDecimal.valueOf(currency.getMultiplier());
        return BigDecimal.valueOf(storeSectionEntry.getPrice()).divide(multiplier, mathContext);
    }

    public static BigDecimal getEntryPriceWithTax(StoreSectionEntry storeSectionEntry) {
        var entryPrice = getEntryPrice(storeSectionEntry);
        var taxPercent = BigDecimal.valueOf(storeSectionEntry.getTaxCategory().getPercent());
        return entryPrice.multiply(taxPercent.add(_100)).divide(_100, mathContext);
    }
}
