package com.jebsen.api.beverageWebAppPoc.entity.quotation;

import com.jebsen.api.beverageWebAppPoc.validator.ContentByte;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class SOInLine implements Serializable {
    
    private BigDecimal LINE_NUMBER;
    private BigDecimal INVENTORY_ITEM_ID;
    @ContentByte(max = 10)
    private String SUBINVENTORY;
    @ContentByte(max = 80)
    private String LOT_NUMBER;
    private BigDecimal ORDERED_QUANTITY;

    private BigDecimal UNIT_SELLING_PRICE;
    @ContentByte(max = 30)
    private String SHIPPING_METHOD;
    private Timestamp SCHEDULE_SHIP_DATE;
    @ContentByte(max = 5)
    private String ORIGINAL_LINE;
    private Timestamp START_DATE;
    
    private Timestamp END_DATE;
    @ContentByte(max = 24000)
    private String REMARK;

}
