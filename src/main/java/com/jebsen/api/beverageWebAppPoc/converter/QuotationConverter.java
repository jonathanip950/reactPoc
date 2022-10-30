package com.jebsen.api.beverageWebAppPoc.converter;

import com.jebsen.api.beverageWebAppPoc.type.PromotionType;
import com.jebsen.api.beverageWebAppPoc.common.Request.CreateQuoteRequest;
import com.jebsen.api.beverageWebAppPoc.entity.quotation.ModifierInRecord;
import com.jebsen.api.beverageWebAppPoc.entity.quotation.SOInHeader;
import com.jebsen.api.beverageWebAppPoc.entity.quotation.SOInLine;
import com.jebsen.api.beverageWebAppPoc.request.quotation.*;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuotationConverter implements Converter<QuotationRequest, CreateQuoteRequest> {
    @Override
    public CreateQuoteRequest convert(@NotNull QuotationRequest source) {
        CreateQuoteRequest target = new CreateQuoteRequest();

        target.setSO_HEADER_IN_RECORD(convertHeader(source));
        target.setSOInLineList(convertLines(source));
        target.setModifierInRecordList(convertOffer(source));

        return target;
    }

    private List<ModifierInRecord> convertOffer(QuotationRequest source) {
        QuotationHeader quotationHeader = source.getQuotationHeader();

        return source.getQuotationLines().stream()
                .map(QuotationLine::getPromotion)
                .map(promotion -> convertPromotion(promotion, quotationHeader))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private List<ModifierInRecord> convertPromotion(Promotion promotion, QuotationHeader quotationHeader) {
        List<ModifierInRecord> modifierInRecords = new ArrayList<>();

        BigDecimal priceNotNull = (BigDecimal) ObjectUtils.defaultIfNull(promotion.getPrice(), BigDecimal.ZERO);
        BigDecimal percentNotNull = (BigDecimal) ObjectUtils.defaultIfNull(promotion.getPercent(), BigDecimal.ZERO);
        BigDecimal buyQtyNotNull = (BigDecimal) ObjectUtils.defaultIfNull(promotion.getBuyQty(), BigDecimal.ZERO);
        BigDecimal getQtyNotNull = (BigDecimal) ObjectUtils.defaultIfNull(promotion.getGetQty(), BigDecimal.ZERO);
        BigDecimal newPriceNotNull = (BigDecimal) ObjectUtils.defaultIfNull(promotion.getNewPrice(), BigDecimal.ZERO);

        Timestamp startDate = quotationHeader.getStartDate() != null ? new Timestamp(quotationHeader.getStartDate().getTime()) : null;
        Timestamp endDate = quotationHeader.getEndDate() != null ? new Timestamp(quotationHeader.getEndDate().getTime()) : null;

        if (startDate == null)
            throw new IllegalArgumentException("Start Date cannot be null");

        if (priceNotNull.intValue() != 0 && percentNotNull.intValue() != 0)
            throw new IllegalArgumentException("Discount Price & Discount Percent cannot have amount at the same time");

        if (priceNotNull.intValue() != 0) {
            modifierInRecords.add(
                    ModifierInRecord.builder()
                        .ORG_ID(promotion.getOrgId())
                        .ORDER_TYPE_ID(promotion.getOrderTypeId())
                        .MODIFIER_TYPE(PromotionType.AMOUNT.getValue())
                        .MODIFIER_START_DATE(startDate)
                        .MODIFIER_END_DATE(endDate)
                        .MODIFIER_ITEM_ID(promotion.getInventoryItemId())
//                        .MODIFIER_VALUE_FROM(promotion.getBuyQty().toString())
//                        .GET_ITEM_ID(promotion.getGetItem())
//                        .GET_PERCENT(promotion.getPercent())
                        .GET_PRICE(priceNotNull)
//                        .GET_QUANTITY(promotion.getGetQty())
//                        .GET_PROMOTION_NATURE(promotion.getNature())
                        .SALES_ACTIVATION(promotion.getSalesActivation())
                        .OVERLAP_FLAG(getOverlap(promotion, quotationHeader.getEndDate()))
//                        .NEW_PRICE(newPriceNotNull)
                        .build()
            );
        }

        if (percentNotNull.intValue() != 0) {
            modifierInRecords.add(
                    ModifierInRecord.builder()
                            .ORG_ID(promotion.getOrgId())
                            .ORDER_TYPE_ID(promotion.getOrderTypeId())
                            .MODIFIER_TYPE(PromotionType.PERCENT.getValue())
                            .MODIFIER_START_DATE(startDate)
                            .MODIFIER_END_DATE(endDate)
                            .MODIFIER_ITEM_ID(promotion.getInventoryItemId())
//                            .MODIFIER_VALUE_FROM(promotion.getBuyQty().toString())
//                            .GET_ITEM_ID(promotion.getGetItem())
                            .GET_PERCENT(percentNotNull)
//                            .GET_PRICE(promotion.getPrice())
//                            .GET_QUANTITY(promotion.getGetQty())
//                            .GET_PROMOTION_NATURE(promotion.getNature())
                            .SALES_ACTIVATION(promotion.getSalesActivation())
                            .OVERLAP_FLAG(getOverlap(promotion, quotationHeader.getEndDate()))
//                            .NEW_PRICE(newPriceNotNull)
                            .build()
            );
        }


        if (buyQtyNotNull.intValue() > 0 && getQtyNotNull.intValue() > 0) {
            modifierInRecords.add(
                    ModifierInRecord.builder()
                            .ORG_ID(promotion.getOrgId())
                            .ORDER_TYPE_ID(promotion.getOrderTypeId())
                            .MODIFIER_TYPE(PromotionType.ITEM.getValue())
                            .MODIFIER_START_DATE(startDate)
                            .MODIFIER_END_DATE(endDate)
                            .MODIFIER_ITEM_ID(promotion.getInventoryItemId())
                            .MODIFIER_VALUE_FROM(buyQtyNotNull.toString())
                            .GET_ITEM_ID(promotion.getGetItem())
//                            .GET_PERCENT(percentNotNull)
//                            .GET_PRICE(priceNotNull)
                            .GET_QUANTITY(getQtyNotNull)
                            .GET_PROMOTION_NATURE(promotion.getNature())
                            .SALES_ACTIVATION(promotion.getSalesActivation())
                            .OVERLAP_FLAG(getOverlap(promotion, quotationHeader.getEndDate()))
//                            .NEW_PRICE(newPriceNotNull)
                            .build()
            );
        }

        if (newPriceNotNull.intValue() > 0) {
            if (promotion.isOverlap()) {
               throw new IllegalArgumentException("new price not valid while overlap flag = true") ;
            }

            modifierInRecords.add(
                    ModifierInRecord.builder()
                            .ORG_ID(promotion.getOrgId())
                            .ORDER_TYPE_ID(promotion.getOrderTypeId())
                            .MODIFIER_TYPE(PromotionType.NEW_PRICE.getValue())
                            .MODIFIER_START_DATE(startDate)
                            .MODIFIER_END_DATE(endDate)
                            .MODIFIER_ITEM_ID(promotion.getInventoryItemId())
//                            .MODIFIER_VALUE_FROM(buyQtyNotNull.toString())
//                            .GET_ITEM_ID(promotion.getGetItem())
//                            .GET_PERCENT(percentNotNull)
//                            .GET_PRICE(priceNotNull)
//                            .GET_QUANTITY(getQtyNotNull)
//                            .GET_PROMOTION_NATURE(promotion.getNature())
                            .SALES_ACTIVATION(promotion.getSalesActivation())
                            .OVERLAP_FLAG(getOverlap(promotion, quotationHeader.getEndDate()))
                            .NEW_PRICE(newPriceNotNull)
                            .build()
            );
        }

        return modifierInRecords;
    }

    private String getOverlap(Promotion promotion, Date endDate) {
        if (org.springframework.util.ObjectUtils.isEmpty(endDate))
            return null;

        return promotion.isOverlap() ? "Y" : "N";
    }

    private List<SOInLine> convertLines(QuotationRequest source) {
        QuotationHeader quotationHeader = source.getQuotationHeader();
        List<QuotationLine> quotationLines = source.getQuotationLines();

        return quotationLines.stream()
                .map(ql -> convertLineObject(ql, quotationHeader))
                .collect(Collectors.toList());
    }

    private SOInLine convertLineObject(QuotationLine quotationLine, QuotationHeader quotationHeader) {
        return SOInLine.builder()
                .INVENTORY_ITEM_ID(quotationLine.getInventoryItemId())
                .ORDERED_QUANTITY(quotationLine.getQty())
                .UNIT_SELLING_PRICE(quotationLine.getUnitSellingPrice())
                .START_DATE(new Timestamp(quotationHeader.getStartDate().getTime()))
                .END_DATE(quotationHeader.getEndDate() == null? null: new Timestamp(quotationHeader.getEndDate().getTime()))
                .LINE_NUMBER(quotationLine.getLineNumber())
                .build();
    }

    private SOInHeader convertHeader(QuotationRequest source) {
        QuotationHeader quotationHeader = source.getQuotationHeader();

        return SOInHeader.builder()
                .SOLD_TO_ORG_ID(quotationHeader.getSoldToOrgId())
                .INVOICE_TO_ORG_ID(quotationHeader.getInvoiceToOrgId())
                .SHIP_TO_ORG_ID(quotationHeader.getShipToOrgId())
                .PRICE_LIST_ID(quotationHeader.getPriceListId())
                .SOLD_FROM_ORG_ID(quotationHeader.getSoldFromOrgId())
                .SHIP_FROM_ORG_ID(quotationHeader.getShipFromOrgId())
                .SALESREP_ID(quotationHeader.getSalesRepId())
                .TRANSACTIONAL_CURR_CODE(quotationHeader.getTransactionalCurrencyCode())
                .PAYMENT_TERM_ID(quotationHeader.getPaymentTerms())
                .SALES_TERMS(quotationHeader.getSalesTerms())
                .ATTENTION(quotationHeader.getAttention())
                .REMARKS(quotationHeader.getRemarks())
                .build();
    }
}
