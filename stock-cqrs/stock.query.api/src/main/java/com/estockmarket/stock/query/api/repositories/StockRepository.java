package com.estockmarket.stock.query.api.repositories;

import com.estockmarket.stock.core.models.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface StockRepository extends CrudRepository<Stock, String> {

    List<Stock> findByCompanyCode(String companyCode);

    @Query(value = "SELECT * FROM stock s WHERE s.create_date_time = (SELECT MAX(s.create_date_time) FROM stock s WHERE s.company_code = :companycode)", nativeQuery = true)
    Stock findLatestStockByCompany(String companycode);

    @Query(value = "SELECT * FROM stock s WHERE s.company_code = :companycode AND s.create_date_time BETWEEN :startdate  AND :enddate", nativeQuery = true)
    List<Stock> findStocksBetweenStartAndEndDates(@Param("companycode") String companycode, @Param("startdate") String startdate, @Param("enddate") String enddate);
//
//    @Query(value = "SELECT MAX(stock_price) FROM stock_cqrs.stock WHERE company_code = :companycode", nativeQuery = true)
//    BigDecimal findMaxStockPrice(@Param("companycode") String companycode);
//
//    @Query(value = "SELECT MIN(stock_price) FROM stock_cqrs.stock WHERE company_code = :companycode", nativeQuery = true)
//    BigDecimal findMinStockPrice(@Param("companycode") String companycode);
//
//    @Query(value = "SELECT AVG(stock_price) FROM stock_cqrs.stock WHERE company_code = :companycode", nativeQuery = true)
//    BigDecimal findAvgStockPrice(@Param("companycode") String companycode);

}
