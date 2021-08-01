package com.estockmarket.stock.controller;

import com.estockmarket.stock.model.Stock;
import com.estockmarket.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/market/stock")
@CrossOrigin(originPatterns = "*")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/add/{companycode}")
    public Stock addStock(@Validated @RequestBody Stock stock, @PathVariable String companycode) {
        return stockService.saveStock(stock, companycode);
    }

    @GetMapping("/getall")
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/get/{companycode}/{startdate}/{enddate}")
    public List<Stock> getStock(@PathVariable String companycode, @PathVariable("startdate") String startdate, @PathVariable("enddate") String enddate) {
        return stockService.findStocksBetweenStartAndEndDates(companycode, startdate, enddate);
    }

    @GetMapping("/{id}")
    public Stock getStock(@PathVariable int id) {
        return stockService.getStock(id);
    }

    @GetMapping("/info/{companycode}")
    public List<Stock> getCompanyByCode(@PathVariable String companycode) {
        return stockService.getCompanyCode(companycode);
    }

    @GetMapping("/info/latest/{companycode}")
    public Optional<Stock> getLatestStockByCompanyCode(@PathVariable String companycode) {
        return stockService.getLatestStockByCompanyCode(companycode);
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
