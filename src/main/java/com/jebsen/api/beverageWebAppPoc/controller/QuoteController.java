package com.jebsen.api.beverageWebAppPoc.controller;


import com.jebsen.api.beverageWebAppPoc.common.Response.ResultCode;
import com.jebsen.api.beverageWebAppPoc.request.quotation.QuotationRequest;
import com.jebsen.api.beverageWebAppPoc.request.quotation.QuotationResult;
import com.jebsen.api.beverageWebAppPoc.service.StoredProcedureService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "QuoteAPI")
@RestController
@Slf4j
@RequestMapping("/Quote")
public class QuoteController extends BaseController {

    @Autowired
    private StoredProcedureService storedProcedureService;

    @PostMapping("/create")
    public ResponseEntity<QuotationResult> createQuote(@Valid @RequestBody QuotationRequest quotationRequest) {
        try {
            return ResponseEntity.ok(storedProcedureService.createQuote(quotationRequest));
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body(QuotationResult.builder().resultCode(ResultCode.OTHER_ERROR).resultMsg(ex.getMessage()).build());
        }
    }
}
