package com.estockmarket.stock.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FindStocksBetweenStartAndEndDatesQuery {
    private String companyCode;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
