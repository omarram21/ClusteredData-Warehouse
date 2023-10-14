package com.ramadan.clusteredData.warehouse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Deal")
public class DealModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deal_id_sequence")
    @SequenceGenerator(name = "deal_id_sequence", sequenceName = "deal_id_sequence", allocationSize = 1)
    private Long dealId;

    @Column(name = "from_currency",nullable = false, length = 2)
    private String fromCurrency;

    @Column(name = "to_currency" ,nullable = false, length = 2)
    private String toCurrency;

    @Column(name = "deal_timestamp")
    private LocalDateTime dealTimestamp;

    @Column(name = "deal_amount",nullable = false)
    private BigDecimal dealAmount;
}
