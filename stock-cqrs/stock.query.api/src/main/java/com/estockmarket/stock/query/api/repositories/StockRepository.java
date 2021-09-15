package com.estockmarket.stock.query.api.repositories;

import com.estockmarket.stock.core.models.Stock;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, String> {

    List<Stock> findByCompanyCode(String companyCode);

}
