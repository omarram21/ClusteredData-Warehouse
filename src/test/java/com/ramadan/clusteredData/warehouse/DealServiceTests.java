package com.ramadan.clusteredData.warehouse;

import com.ramadan.clusteredData.warehouse.Repository.DealRepository;
import com.ramadan.clusteredData.warehouse.model.DTO.DealDTO;
import com.ramadan.clusteredData.warehouse.model.DealModel;
import com.ramadan.clusteredData.warehouse.service.DealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DealServiceTests {

    @InjectMocks
    private DealService dealService;

    @Mock
    private DealRepository dealRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddNewDealWithValidData() {
        DealDTO request = new DealDTO();
        request.setFromCurrency("USD");
        request.setToCurrency("EUR");
        request.setDealTimestamp(LocalDateTime.now());
        request.setDealAmount(BigDecimal.TEN);

        when(dealRepository.existsByFromCurrencyAndToCurrencyAndDealTimestampAndDealAmount(
                request.getFromCurrency(),
                request.getToCurrency(),
                request.getDealTimestamp(),
                request.getDealAmount()
        )).thenReturn(false);

        ResponseEntity response = dealService.addNewDeal(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddNewDealWithInvalidData() {
        DealDTO request = new DealDTO();
        // Set invalid data

        ResponseEntity response = dealService.addNewDeal(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


}
