package com.estockmarket.estockmarketapp.common;

import com.estockmarket.estockmarketapp.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TranResWithAllStocks {

    private Company company;
    private List<Stock> stock;
}
