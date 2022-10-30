package com.jebsen.api.beverageWebAppPoc.entity.priceQuery;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@ToString
public class PriceQueryResult implements Serializable {
    private String brand;
    private String product;
    private String sfa_product_id;
    private BigDecimal list_price;
    private BigDecimal discount_amt;
    private Timestamp start_date;
    private Timestamp end_date;
    private BigDecimal bucket;
    private BigDecimal delivery_allowance;
    private BigDecimal buy_x;
    private BigDecimal get_offer;
    private Timestamp buy_x_start_date;
    private Timestamp buy_x_end_date;
    private BigDecimal net_price;
    private String remark;
    private String msg_code;
    private String MESSAGE;
    private BigDecimal discount_percentage;
    private BigDecimal fixed_price;
    private String get_sfa_product_id;
    private String overlap_flag;
    private String offer_type;
    private String wine_of_the_month;
    private String remark2;
    private String remark3;
    private String sku_description;
    private String foc_sku_description;
    private String key;

    public double getNet_price() {
        return Math.ceil(net_price.doubleValue());
    }
}
