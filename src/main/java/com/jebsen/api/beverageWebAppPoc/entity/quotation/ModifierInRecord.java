package com.jebsen.api.beverageWebAppPoc.entity.quotation;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ModifierInRecord implements Serializable {
    
    private BigDecimal ORG_ID;
    private BigDecimal BILL_TO_SITE_ID;
    private BigDecimal SHIP_TO_SITE_ID;
    private BigDecimal ORDER_TYPE_ID;
    private BigDecimal QUOTATION_ID;
    
    private BigDecimal MODIFIER_NUMBER;
    private BigDecimal MODIFIER_TYPE;
    private Timestamp MODIFIER_START_DATE;
    private Timestamp MODIFIER_END_DATE;
    private BigDecimal MODIFIER_ITEM_ID;
    
    private String MODIFIER_VALUE_FROM;
    private BigDecimal  GET_ITEM_ID;
    private String GET_ITEM_UOM;
    private BigDecimal GET_PERCENT;
    private BigDecimal GET_PRICE;

    private BigDecimal GET_QUANTITY;
    private String GET_PROMOTION_NATURE;
    private String SALES_ACTIVATION;
    private BigDecimal NEW_PRICE;
    private String OVERLAP_FLAG;
}
