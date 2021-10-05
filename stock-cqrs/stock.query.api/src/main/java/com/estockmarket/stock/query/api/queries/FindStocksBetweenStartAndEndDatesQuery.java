package com.estockmarket.stock.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindStocksBetweenStartAndEndDatesQuery {
    private String companyCode;
    private String startDate;
    private String endDate;
}
