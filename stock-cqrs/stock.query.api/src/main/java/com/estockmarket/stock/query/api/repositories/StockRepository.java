package com.estockmarket.stock.query.api.repositories;

import com.estockmarket.stock.core.models.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Integer> {
}
