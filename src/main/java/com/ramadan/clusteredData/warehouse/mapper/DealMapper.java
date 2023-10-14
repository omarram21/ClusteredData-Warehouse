package com.ramadan.clusteredData.warehouse.mapper;

import com.ramadan.clusteredData.warehouse.model.DTO.DealDTO;
import com.ramadan.clusteredData.warehouse.model.DealModel;
import com.ramadan.clusteredData.warehouse.service.DealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DealMapper {
    private static final Logger logger = LoggerFactory.getLogger(DealService.class);
    public static DealModel mapDealDto(DealDTO dto){
        DealModel deal = new DealModel();
        try{
            deal.setDealAmount(dto.getDealAmount());
            deal.setFromCurrency(dto.getFromCurrency().toUpperCase());
            deal.setToCurrency(dto.getToCurrency().toUpperCase());
            deal.setDealTimestamp(dto.getDealTimestamp());
            return deal;
        }
        catch(Exception e){
            logger.error("Exception in Mapping deal dto");
            return null;
        }
    }
}
