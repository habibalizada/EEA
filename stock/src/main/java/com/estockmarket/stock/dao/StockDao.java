package com.estockmarket.stock.dao;

import com.estockmarket.stock.model.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockDao extends CrudRepository<Stock, Integer> {

    List<Stock> findByCompanyCode(String companyCode);

//    void deleteByCompanyCode(String companycode);

    long removeByCompanyCode(String companyCode);
}
