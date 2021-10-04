package com.estockmarket.estockmarketapp.client;

import com.estockmarket.estockmarketapp.common.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("STOCK-QUERY/api/v1.0/market/stock/query")
public interface StockQueryFeignClient {

    @GetMapping("/getall")
    List<Stock> getAllStocks();

    @GetMapping("/info/latest/{companycode}")
    Stock getLatestStockByCompanyCode(@PathVariable String companycode);

    @GetMapping("/info/{companycode}")
    List<Stock> getStocksByCompanyCode(@PathVariable String companycode);
}
