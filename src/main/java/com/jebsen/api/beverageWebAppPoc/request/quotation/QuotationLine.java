package com.jebsen.api.beverageWebAppPoc.request.quotation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class QuotationLine{
    private BigDecimal inventoryItemId;
    private BigDecimal unitSellingPrice;
    private BigDecimal qty;
    private BigDecimal lineNumber;
    private Promotion promotion;
}
