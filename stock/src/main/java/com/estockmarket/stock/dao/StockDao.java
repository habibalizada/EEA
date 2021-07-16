package com.estockmarket.stock.dao;

import com.estockmarket.stock.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockDao extends CrudRepository<Stock, Integer> {

    Stock findByCompanyCode(String companyCode);
}
