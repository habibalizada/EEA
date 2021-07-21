package com.estockmarket.stock.dao;

import com.estockmarket.stock.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;

@EnableJpaRepositories
public interface StockDao extends JpaRepository<Stock, Integer> {

    List<Stock> findByCompanyCode(String companyCode);

    @Query(value = "SELECT * FROM stock s WHERE s.company_code = :companycode AND s.create_date_time BETWEEN :startdate  AND :enddate", nativeQuery = true)
    List<Stock> findStocksBetweenStartAndEndDates(@Param("companycode") String companycode, @Param("startdate") String startdate, @Param("enddate") String enddate);

}
