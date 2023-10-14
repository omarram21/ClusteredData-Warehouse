package com.ramadan.clusteredData.warehouse.controller;

import com.ramadan.clusteredData.warehouse.model.DTO.DealDTO;
import com.ramadan.clusteredData.warehouse.model.DealModel;
import com.ramadan.clusteredData.warehouse.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/deals",produces = MediaType.APPLICATION_JSON_VALUE)
public class DealController {
    @Autowired
    private final DealService dealService;
    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping
    public ResponseEntity<List<DealModel>> getAllDeals() {
        return dealService.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealModel> getDealById(@PathVariable(name = "id", required = true) Long id) {
        return dealService.GetByID(id);
    }

    @PostMapping
    public ResponseEntity<DealModel> addNewDeal(@Valid @RequestBody DealDTO request) {
        return dealService.addNewDeal(request);
    }
}
