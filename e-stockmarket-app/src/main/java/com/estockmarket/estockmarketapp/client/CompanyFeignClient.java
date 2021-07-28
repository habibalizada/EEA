package com.estockmarket.estockmarketapp.client;

import com.estockmarket.estockmarketapp.common.StockRequest;
import com.estockmarket.estockmarketapp.common.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@FeignClient(url = "http://localhost:9191/api/v1.0/market/stock", name = "COMPANY-CLIENT")
@FeignClient("STOCK-SERVICE/api/v1.0/market/stock")
public interface CompanyFeignClient {

    @PostMapping("add/{companycode}")
    Stock addStock(@RequestBody StockRequest stockRequest, @PathVariable("companycode") String companycode);

    @GetMapping("/getall")
    List<Stock> getAllStocks();

    @GetMapping("/info/latest/{companycode}")
    Optional<Stock> getLatestStockByCompanyCode(@PathVariable String companycode);

    @DeleteMapping("/delete/{companycode}")
    void deleteStockByCompanyCode(@PathVariable String companycode);
}
