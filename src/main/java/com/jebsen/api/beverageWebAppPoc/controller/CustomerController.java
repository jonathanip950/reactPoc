package com.jebsen.api.beverageWebAppPoc.controller;

import com.jebsen.api.beverageWebAppPoc.request.customer.CustomerRequest;
import com.jebsen.api.beverageWebAppPoc.entity.customer.CustomerResult;
import com.jebsen.api.beverageWebAppPoc.service.StoredProcedureService;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Customer API")
@RestController
@Slf4j
@RequestMapping("/Customer")
public class CustomerController extends BaseController {
    @Autowired
    StoredProcedureService storedProcedureService;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<List<CustomerResult>> getCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
//        return ResponseEntity.ok().body(itemOnHandStoredProcedure.execute());
        log.info("in /CustomerList");
        return ResponseEntity.ok().body(storedProcedureService.getCustomerList(customerRequest.getSalesRepADName()));
    }

//    @GetMapping("/v2")
//    @SneakyThrows
//    public ResponseEntity<Map<BigDecimal, List<Customer>>> getCustomerV2() {
////        return ResponseEntity.ok().body(itemOnHandStoredProcedure.execute());
//        log.info("in /CustomerList v2");
//        Map<BigDecimal, List<Customer>> customerMap = storedProcedureService.getCustomerList(2899, 100009212, "VS")
//                .stream()
//                .collect(
//                        Collectors.groupingBy(Customer::getCUST_ACCOUNT_ID, HashMap::new, Collectors.toList())
//                );
//        customerMap.get(new BigDecimal("20012437")).add(new Customer());
//
//        customerMap.entrySet().stream().filter(f -> f.getValue().size() > 1).forEach(f1 -> log.info("key: {}", f1.getKey()));
//        return ResponseEntity.ok().body(customerMap);
//    }
}
