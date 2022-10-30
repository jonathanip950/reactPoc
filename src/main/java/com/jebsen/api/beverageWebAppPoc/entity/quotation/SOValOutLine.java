package com.jebsen.api.beverageWebAppPoc.entity.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class SOValOutLine implements Serializable {
    @JsonProperty
    private BigDecimal HEADER_ID;
    @JsonProperty
    private BigDecimal LINE_ID;
    @JsonProperty
    private BigDecimal LINE_NUMBER;
    @JsonProperty
    private BigDecimal INVENTORY_ITEM_ID;
    @JsonProperty
    private String SUBINVENTORY;

    @JsonProperty
    private String PRODUCT;
    @JsonProperty
    private String PRODUCT_DESCRIPTION;
    @JsonProperty
    private BigDecimal ORDERED_QUANTITY;
    @JsonProperty
    private BigDecimal UNIT_SELLING_PRICE;
    @JsonProperty
    private String UOM;
    @JsonProperty
    private List<ITEM_LOT_RECORD> LOT_NUMBER_TBL;
    @JsonProperty
    private String SHIPPING_METHOD;
    @JsonProperty
    private String SCHEDULE_SHIP_DATE;
    @JsonProperty
    private String ORIGINAL_LINE;
    @JsonProperty
    private String DRAFT_BEER_FLAG;

}
