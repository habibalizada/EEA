package com.estockmarket.estockmarketapp.client;

import com.estockmarket.estockmarketapp.common.Stock;
import com.estockmarket.estockmarketapp.common.StockRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("STOCK-COMMAND/api/v1.0/market/stock/command")
public interface CompanyFeignClient {

    @PostMapping("add/{companycode}")
    Stock addStock(@RequestBody StockRequest stockRequest, @PathVariable("companycode") String companycode);

    @DeleteMapping("/delete/{companycode}")
    void deleteStockByCompanyCode(@PathVariable String companycode);

    @PutMapping("/update")
    void updateStock(@RequestBody Stock stocks);
}
