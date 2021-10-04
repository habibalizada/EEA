package com.estockmarket.estockmarketapp.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {
    private String id;
    private String companyCode;
    private BigDecimal stockPrice;
    private LocalDateTime createDateTime;
}
