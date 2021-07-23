package com.estockmarket.stock.service;

import com.estockmarket.stock.dao.StockDao;
import com.estockmarket.stock.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    @Autowired
    private StockDao stockDao;

    //saveStock
    public Stock saveStock(Stock stock, String companycode) {
        stock.setCompanyCode(companycode);
        return stockDao.save(stock);
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

    /**
     * In this method date is used in "yyyy-MM-dd'T'HH:mm:ss" example:
     * http://localhost:9191/api/v1.0/market/stock/get/APP02/2017-08-26T00:00:01/2021-07-19T23:59:59
     *
     * @param companycode
     * @param startdate
     * @param enddate
     * @return List of Stocks
     */
    public List<Stock> getStockBetweenStartAndEnddate(String companycode, String startdate, String enddate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime startDateParsed = LocalDateTime.parse(startdate, formatter);
        LocalDateTime endDatePrsed = LocalDateTime.parse(enddate, formatter);
        List<Stock> stockList = stockDao.findByCompanyCode(companycode);
        return stockList
                .stream()
                .filter(stock -> stock.getCreateDateTime().isAfter(startDateParsed) && stock.getCreateDateTime().isBefore(endDatePrsed))
                .collect(Collectors.toList());
    }

    public List<Stock> findStocksBetweenStartAndEndDates(String companycode, String startdate, String enddate) {
        return stockDao.findStocksBetweenStartAndEndDates(companycode, startdate, enddate);
    }
}
