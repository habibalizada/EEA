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

    //getAllStocks
    public List<Stock> getAllStocks() {
        return (List<Stock>) stockDao.findAll();
    }

    //getByCompanyCode
    public List<Stock> getCompanyCode(String companycode) {
        return stockDao.findByCompanyCode(companycode);
    }

    //deleteStock by companyCode
    public void deleteStockByCompanyCode(String companycode) {
        List<Stock> stockList = stockDao.findByCompanyCode(companycode);
        stockDao.deleteAll(stockList);
    }

    //updateStock
    public Stock updateStock(Stock stock) {
        return stockDao.save(stock);
    }


    public Stock getStock(int id) {
        return stockDao.findById(id).get();
    }
}
