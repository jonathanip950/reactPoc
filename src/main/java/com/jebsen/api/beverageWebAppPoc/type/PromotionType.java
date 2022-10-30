package com.jebsen.api.beverageWebAppPoc.type;

import java.math.BigDecimal;

public enum PromotionType {
    ITEM(1),
    PERCENT(2),
    AMOUNT(3),
    NEW_PRICE(4);

    private final BigDecimal value;

    PromotionType(final int value) {
        this.value = new BigDecimal(value);
    }

    public BigDecimal getValue() {
        return value;
    }
}
