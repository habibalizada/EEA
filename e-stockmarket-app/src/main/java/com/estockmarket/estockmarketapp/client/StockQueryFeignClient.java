package com.estockmarket.estockmarketapp.client;

import com.estockmarket.estockmarketapp.common.Stock;
import com.estockmarket.estockmarketapp.common.StockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(url = "http://localhost:9191/api/v1.0/market/stock", name = "COMPANY-CLIENT")
@FeignClient("STOCK-QUERY/api/v1.0/market/stock/query")
public interface StockQueryFeignClient {

    @GetMapping("/getall")
    List<Stock> getAllStocks();

    @GetMapping("/info/latest/{companycode}")
    Stock getLatestStockByCompanyCode(@PathVariable String companycode);

    @GetMapping("/info/{companycode}")
    List<Stock> getCompanyByCode(@PathVariable String companycode);
}
