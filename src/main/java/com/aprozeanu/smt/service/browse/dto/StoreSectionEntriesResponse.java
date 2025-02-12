package com.aprozeanu.smt.service.browse.dto;

import com.aprozeanu.smt.model.price.Pricing;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;

public record StoreSectionEntriesResponse(Page<StoreSectionEntry> entries) {
    public static StoreSectionEntriesResponse from(Page<com.aprozeanu.smt.model.store.StoreSectionEntry> storeSectionEntryById) {
        return new StoreSectionEntriesResponse(storeSectionEntryById.map(StoreSectionEntriesResponse::storeSectionEntry));
    }

    private static StoreSectionEntry storeSectionEntry(com.aprozeanu.smt.model.store.StoreSectionEntry storeSectionEntry) {
        var id = storeSectionEntry.getId();
        var productId = storeSectionEntry.getProduct().getId();
        var name = storeSectionEntry.getProduct().getName();
        var priceBeforeTax = formatPriceForEntry(Pricing.getEntryPrice(storeSectionEntry),
            storeSectionEntry);
        var priceAfterTax = formatPriceForEntry(Pricing.getEntryPriceWithTax(storeSectionEntry),
            storeSectionEntry);
        return new StoreSectionEntry(id, productId, name, priceBeforeTax, priceAfterTax);
    }

    private static NumberFormat getNumberFormat(com.aprozeanu.smt.model.price.Currency currency) {
        var numberFormat = NumberFormat.getCurrencyInstance();
        numberFormat.setCurrency(Currency.getInstance(currency.getSymbol()));
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat;
    }

    private static String formatPriceForEntry(BigDecimal value,
                                              com.aprozeanu.smt.model.store.StoreSectionEntry storeSectionEntry) {
        var currency = storeSectionEntry.getStoreSection().getStore().getMarket().getCurrency();
        return getNumberFormat(currency).format(value);
    }

    record StoreSectionEntry(Long entryId, Long productId, String productName, String priceBeforeTax,
                             String priceAfterTax) {
    }
}
