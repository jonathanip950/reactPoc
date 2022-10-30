package com.jebsen.api.beverageWebAppPoc.controller;

import com.jebsen.api.beverageWebAppPoc.entity.itemOnHand.OnHandRecord;
import com.jebsen.api.beverageWebAppPoc.entity.itemOnHand.OnHandRecordResult;
import com.jebsen.api.beverageWebAppPoc.service.StoredProcedureService;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Item On Hand API")
@RestController
@Slf4j
@RequestMapping("/ItemOnHand")
public class ItemOnHandController extends BaseController {
    @Autowired
    StoredProcedureService storedProcedureService;

    @PostMapping
    @SneakyThrows
    public ResponseEntity<List<OnHandRecordResult>> getItemOnHand(@Valid @RequestBody OnHandRecord onHandRecord) {
//        return ResponseEntity.ok().body(itemOnHandStoredProcedure.execute());
        log.info("in /ItemOnHand");
        return ResponseEntity.ok().body(storedProcedureService.getItemOnHand(onHandRecord.getOrgId(), onHandRecord.getItemId()));
    }
}
