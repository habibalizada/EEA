package com.estockmarket.stock.core.events;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class StockCreatedEvent {
    private int id;
    private String companyCode;
    private BigDecimal stockPrice;
    private LocalDateTime createDateTime;
}
