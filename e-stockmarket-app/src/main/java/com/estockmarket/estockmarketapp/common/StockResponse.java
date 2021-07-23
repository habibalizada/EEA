package com.estockmarket.estockmarketapp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockResponse {
    private int id;
    private String companyCode;
    private BigDecimal stockPrice;
    private LocalDateTime createDateTime;
}
