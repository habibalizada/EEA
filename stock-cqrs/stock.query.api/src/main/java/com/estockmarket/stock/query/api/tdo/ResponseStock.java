package com.estockmarket.stock.query.api.tdo;

import com.estockmarket.stock.core.models.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStock {
    private List<Stock> stocks;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal avePrice;
}
