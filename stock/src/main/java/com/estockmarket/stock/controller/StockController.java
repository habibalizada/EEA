package com.estockmarket.stock.controller;

import com.estockmarket.stock.model.Stock;
import com.estockmarket.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/market/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("add/{companycode}")
    public String addStock(@Validated @RequestBody Stock stock, @PathVariable String companycode) {
        stockService.saveStock(stock, companycode);
        return "Stock created for " + companycode;
    }

    @GetMapping("/getall")
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{id}")
    public Stock getStock(@PathVariable(name = "id") int id) {
        return stockService.getStock(id);
    }

    @GetMapping("/info/{companycode}")
    public List<Stock> getCompanyByCode(@PathVariable String companycode) {
        return stockService.getCompanyCode(companycode);
    }

    @DeleteMapping("/delete/{companycode}")
    public void deleteStockByCompanyCode(@PathVariable String companycode) {
        stockService.deleteStockByCompanyCode(companycode);
    }

    @PutMapping("/update")
    public Stock updateStock(@RequestBody Stock stock) {
        return stockService.updateStock(stock);
    }
}
