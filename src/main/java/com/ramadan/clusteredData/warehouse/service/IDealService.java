package com.ramadan.clusteredData.warehouse.service;

import com.ramadan.clusteredData.warehouse.model.DTO.DealDTO;
import com.ramadan.clusteredData.warehouse.model.DealModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IDealService {
    ResponseEntity<List<DealModel>> GetAll();
    ResponseEntity<DealModel> GetByID(Long id);
    ResponseEntity addNewDeal(DealDTO request);
}
