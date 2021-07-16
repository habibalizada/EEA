package com.estockmarket.stock.service;

import com.estockmarket.stock.dao.StockDao;
import com.estockmarket.stock.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockDao stockDao;

    //saveStock
    public void saveStock(Stock stock, String companycode) {
        stock.setCompanyCode(companycode);
        stockDao.save(stock);
    }

    //getStockById
    //getByCompanyCode
    //getAllStocks
    public List<Stock> getAllStocks() {
        return (List<Stock>) stockDao.findAll();
    }
    //updateStock
    //deleteStock
}
