package com.jebsen.api.beverageWebAppPoc.entity.itemOnHand;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ReservationRecordResult implements Serializable {
    private BigDecimal orgId;
    private BigDecimal itemId;
    private String customerName;
    private BigDecimal reservationQuantity;
}
