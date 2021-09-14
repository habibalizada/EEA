package com.estockmarket.stock.core.events;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockUpdatedEvent {
    private int id;
    private String companyCode;
}
