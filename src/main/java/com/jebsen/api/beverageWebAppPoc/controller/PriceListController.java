package com.jebsen.api.beverageWebAppPoc.controller;

import com.jebsen.api.beverageWebAppPoc.common.Request.CreateQuoteRequest;
import com.jebsen.api.beverageWebAppPoc.converter.QuotationConverter;
import com.jebsen.api.beverageWebAppPoc.entity.priceList.PriceListResult;
import com.jebsen.api.beverageWebAppPoc.request.priceList.PriceListRequest;
import com.jebsen.api.beverageWebAppPoc.request.quotation.QuotationRequest;
import com.jebsen.api.beverageWebAppPoc.service.StoredProcedureService;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags = "Price List API")
@RestController
@Slf4j
@RequestMapping("/PriceList")
public class PriceListController extends BaseController {

    @Autowired
    private StoredProcedureService storedProcedureService;

    @Autowired
    private QuotationConverter quotationConverter;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<List<PriceListResult>> getPriceList(@Valid @RequestBody PriceListRequest priceListRequest) {
        log.info("in /PriceList, param: salesRepADName - {}", priceListRequest.getSalesRepADName());
//        return ResponseEntity.ok().body(priceListStoredProcedure.execute(sqlPriceListIdTblList));
        return ResponseEntity.ok().body(storedProcedureService.getPriceListFromPLSQL(priceListRequest.getSalesRepADName()));
    }

    @PostMapping("/cache/update")
    public ResponseEntity<List<PriceListResult>> putPriceListCache(@Valid @RequestBody PriceListRequest priceListRequest) {
        log.info("in /PriceList/cache/update/, param: salesRepADName - {}", priceListRequest.getSalesRepADName());
        List<PriceListResult> priceListResults = storedProcedureService.updatePriceListCache(priceListRequest.getSalesRepADName());
        return ResponseEntity.ok().body(priceListResults);
    }

    @PostMapping("/cache/remove")
    public ResponseEntity<Map<String, String>> removePriceListCache(@Valid @RequestBody PriceListRequest priceListRequest) {
        log.info("in /PriceList/cache/remove/, param: salesRepADName - {}", priceListRequest.getSalesRepADName());
        return ResponseEntity.ok().body(storedProcedureService.removePriceListCache(priceListRequest.getSalesRepADName()));
    }

    @PostMapping("/cache/remove/all")
    public ResponseEntity<Map<String, String>> removePriceListCache() {
        log.info("in /PriceList/remove/PriceList/all");
        return ResponseEntity.ok().body(storedProcedureService.removeAllPriceListCache());
    }

    @PostMapping("/checkout")
    public ResponseEntity<CreateQuoteRequest> mockCheckout(@Valid @RequestBody QuotationRequest quotationRequest) {
        return ResponseEntity.ok(quotationConverter.convert(quotationRequest));
    }
}
