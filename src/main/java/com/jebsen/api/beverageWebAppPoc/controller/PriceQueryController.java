package com.jebsen.api.beverageWebAppPoc.controller;

import com.jebsen.api.beverageWebAppPoc.request.promotionQuery.PriceQueryRequest;
import com.jebsen.api.beverageWebAppPoc.entity.priceQuery.PriceQueryResult;
import com.jebsen.api.beverageWebAppPoc.service.StoredProcedureService;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Price Query API")
@RestController
@Slf4j
@RequestMapping("/PriceQuery")
public class PriceQueryController extends BaseController {
    @Autowired
    StoredProcedureService storedProcedureService;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<List<PriceQueryResult>> getPromotionQuery(@Valid @RequestBody PriceQueryRequest priceQueryRequest) {
//        return ResponseEntity.ok().body(itemOnHandStoredProcedure.execute());
        log.info("in /PriceQuery");
        return ResponseEntity.ok().body(storedProcedureService.getPriceQuery(priceQueryRequest));
    }
}
