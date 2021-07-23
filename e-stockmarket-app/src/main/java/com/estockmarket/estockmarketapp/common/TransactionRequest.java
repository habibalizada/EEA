package com.estockmarket.estockmarketapp.common;

import com.estockmarket.estockmarketapp.model.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private Company company;
    private StockRequest stockRequest;
}
