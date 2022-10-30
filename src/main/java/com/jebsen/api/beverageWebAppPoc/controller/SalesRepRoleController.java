package com.jebsen.api.beverageWebAppPoc.controller;

import com.jebsen.api.beverageWebAppPoc.entity.salesRepRole.SalesRepRoleResult;
import com.jebsen.api.beverageWebAppPoc.request.salesRepRole.SalesRepRoleRequest;
import com.jebsen.api.beverageWebAppPoc.service.StoredProcedureService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Customer API")
@RestController
@Slf4j
@RequestMapping("/SalesRepRole")
public class SalesRepRoleController extends BaseController {

    @Autowired
    StoredProcedureService storedProcedureService;

    @RequestMapping
    public ResponseEntity<List<SalesRepRoleResult>> getSalesRepRole(@RequestBody SalesRepRoleRequest salesRepRoleRequest) {
        return ResponseEntity.ok(storedProcedureService.getSalesRepRole(salesRepRoleRequest.getSalesRepADName()));
    }
}
