package com.estockmarket.stock.query.api.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindStocksByCompanyCodeQuery {
    private String companyCode;
}
