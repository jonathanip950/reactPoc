package com.jebsen.api.beverageWebAppPoc.service;

import com.codepoetics.protonpack.StreamUtils;
import com.jebsen.api.beverageWebAppPoc.converter.QuotationConverter;
import com.jebsen.api.beverageWebAppPoc.common.Request.*;
import com.jebsen.api.beverageWebAppPoc.entity.customer.CustomerResult;
import com.jebsen.api.beverageWebAppPoc.entity.itemOnHand.OnHandRecordResult;
import com.jebsen.api.beverageWebAppPoc.entity.priceList.PriceListResult;
import com.jebsen.api.beverageWebAppPoc.entity.salesRepRole.SalesRepRoleResult;
import com.jebsen.api.beverageWebAppPoc.request.promotionQuery.PriceQueryRequest;
import com.jebsen.api.beverageWebAppPoc.entity.priceQuery.PriceQueryResult;
import com.jebsen.api.beverageWebAppPoc.request.quotation.QuotationRequest;
import com.jebsen.api.beverageWebAppPoc.request.quotation.QuotationResult;
import com.jebsen.api.beverageWebAppPoc.storedProcedure.*;
import com.jebsen.api.beverageWebAppPoc.type.DivisionType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import oracle.sql.TIMESTAMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.sql.SQLDataException;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StoredProcedureService {
    @Autowired
    ItemOnHandStoredProcedure itemOnHandStoredProcedure;

    @Autowired
    PriceListStoredProcedure priceListStoredProcedure;

    @Autowired
    CustomerStoredProcedure customerStoredProcedure;

    @Autowired
    CreateQuoteProcedure createQuoteProcedure;

    @Autowired
    PriceQueryProcedure priceQueryProcedure;

    @Autowired
    ProductReservationProcedure productReservationProcedure;

    @Autowired
    QuotationConverter quotationConverter;


    @Cacheable(value = "priceList", key = "#salesRepADName")
    @SneakyThrows
    public List<PriceListResult> getPriceListFromPLSQL(String salesRepADName) {
        log.info("getting Price List from PL/SQL, salesRepADName: {}", salesRepADName);
        List<PriceListResult> priceListResults = priceListStoredProcedure.execute(salesRepADName);

        log.info("priceListResults: {}", priceListResults);
        return priceListResults;
    }

    @CachePut(value = "priceList", key = "#salesRepADName")
    public List<PriceListResult> updatePriceListCache(String salesRepADName) {
        log.info("updating Price List cache, salesRepADName: {}", salesRepADName);
        return priceListStoredProcedure.execute(salesRepADName);
    }

    @CacheEvict(value = "priceList", key = "#salesRepADName")
    public Map<String, String> removePriceListCache(String salesRepADName) {
        log.info("remove Price List cache, salesRepADName: {}", salesRepADName);

        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("orgId", salesRepADName);

        return map;
    }

    @CacheEvict(value = "priceList", allEntries = true)
    public Map<String, String> removeAllPriceListCache() {
        log.info("remove all Price List cache");

        HashMap<String, String> map = new HashMap<>();
        map.put("status", "success");

        return map;
    }

    @Cacheable(value = "itemOnHand", key = "{#orgId,#itemId}")
    public List<OnHandRecordResult> getItemOnHand(int orgId, int itemId) {
        log.info("getting Item On Hand from PL/SQL, orgId: {}, itemId: {}", orgId, itemId);

        List<OnHandRecordResult> onHandRecordResults = itemOnHandStoredProcedure.execute(orgId, itemId);

        onHandRecordResults
                .forEach(onHandRecord -> onHandRecord.setReservationRecords(productReservationProcedure._execute(onHandRecord)));

        log.info("onHandRecordResults: {}", onHandRecordResults);
        return onHandRecordResults;
    }

    @Cacheable(value = "customer", key = "#salesRepADName")
    public List<CustomerResult> getCustomerList(String salesRepADName) {
        log.info("getting Customer from PL/SQL, salesRepADName: {}", salesRepADName);

        return customerStoredProcedure.execute(salesRepADName);
    }

    public List<SalesRepRoleResult> getSalesRepRole(String salesRepADName) {
        log.info("getting Sales Rep Role from PL/SQL, salesRepADName: {}", salesRepADName);
        List<SalesRepRoleResult> roleResults = new ArrayList<>();

        Set<Integer> orgIdSet = getCustomerList(salesRepADName).stream()
                .map(CustomerResult::getORG_ID)
                .map(BigDecimal::intValue)
                .collect(Collectors.toSet());

        for (DivisionType division : DivisionType.values()) {
            if (orgIdSet.contains(division.getOrgId())) {
                roleResults.add(new SalesRepRoleResult(division.getDivision(), division.getOrgId()));
            }
        }

        log.info("roleResults: {}", roleResults);
        return roleResults;
    }

    public QuotationResult createQuote(QuotationRequest quotationRequest) throws Exception {
        log.info("## createQuote to oracle");

        log.info("quotationRequest: {}", quotationRequest);
        CreateQuoteRequest createQuoteRequest = quotationConverter.convert(quotationRequest);
        log.info("createQuoteRequest: {}", createQuoteRequest);

        QuotationResult quotationResult = createQuoteProcedure.execute(
                createQuoteRequest.getSO_HEADER_IN_RECORD(),
                createQuoteRequest.getSOInLineList(),
                createQuoteRequest.getModifierInRecordList());

        log.info("quotationResult: {}", quotationResult);
        return quotationResult;
    }

    public List<PriceQueryResult> getPriceQuery(PriceQueryRequest priceQueryRequest) throws SQLDataException {
        log.info("getting Promotion Query from PL/SQL");

        List<PriceQueryResult> priceQueryResults = priceQueryProcedure.execute(priceQueryRequest);

        if (priceQueryResults.size() == 1 && !priceQueryResults.get(0).getMsg_code().equals("0")) {
            if (priceQueryResults.get(0).getMESSAGE() != null && priceQueryResults.get(0).getMESSAGE().startsWith("ORA-01403")) {
                return new ArrayList<>();
            }

            throw new SQLDataException(MessageFormat.format("SQL Error: {0}", priceQueryResults.get(0).getMESSAGE()));
        }

        Date now = new Date();

        List<PriceQueryResult> priceQueryWithReservationResult = StreamUtils.zipWithIndex(priceQueryResults.stream()
                        .filter(priceQueryResult -> ObjectUtils.isEmpty(priceQueryResult.getStart_date()) || priceQueryResult.getStart_date().before(now))
                        .filter(priceQueryResult -> ObjectUtils.isEmpty(priceQueryResult.getEnd_date()) || priceQueryResult.getEnd_date().after(now))
                        .sorted(
                                Comparator.comparing(PriceQueryResult::getStart_date, Comparator.nullsFirst(Timestamp::compareTo))
                                        .thenComparing(PriceQueryResult::getEnd_date, Comparator.nullsFirst(Timestamp::compareTo))
                                        .thenComparing(PriceQueryResult::getProduct).reversed()
                        )
                ).map(s -> {
                    PriceQueryResult result = s.getValue();
                    // set custom key to avoid warning from muix from frontend
                    result.setKey(MessageFormat.format("price-query-key-{0}", s.getIndex()));
                    return result;
                })
                .collect(Collectors.toList());

        log.info("priceQueryWithReservationResult: {}", priceQueryWithReservationResult);
        return priceQueryWithReservationResult;
    }
}
