package com.jebsen.api.beverageWebAppPoc.entity.itemOnHand;

import lombok.Data;

@Data
public class OnHandRecord {
    private int orgId;
    private int itemId;
    private String itemSubInv;
}
