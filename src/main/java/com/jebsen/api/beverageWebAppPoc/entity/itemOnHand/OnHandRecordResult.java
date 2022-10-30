package com.jebsen.api.beverageWebAppPoc.entity.itemOnHand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class OnHandRecordResult implements Serializable {
    private BigDecimal ORG_ID;
    private String ORGANIZATION_NAME;
    private BigDecimal ITEM_ID;
    private String ITEM_CODE;
    private String DESCRIPTION;
    private String SUBINVENTORY;
    private String LOT;
    private String UOM;
    private BigDecimal ONHAND_QTY;
    private BigDecimal RESRV_QTY;
    private BigDecimal AVLB_QTY;

    private List<ReservationRecordResult> reservationRecords;

    @JsonProperty(value = "lot")
    public String getLOT() {
        return !this.LOT.equals("-1")? this.LOT: null;
    }
}
