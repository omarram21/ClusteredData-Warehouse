package com.ramadan.clusteredData.warehouse.Repository;

import com.ramadan.clusteredData.warehouse.model.DealModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface DealRepository extends JpaRepository<DealModel,Long> {
    boolean existsByFromCurrencyAndToCurrencyAndDealTimestampAndDealAmount(
            String fromCurrency,
            String toCurrency,
            LocalDateTime dealTimestamp,
            BigDecimal dealAmount
    );
}
