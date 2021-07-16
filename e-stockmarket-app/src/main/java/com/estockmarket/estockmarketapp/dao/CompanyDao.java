package com.estockmarket.estockmarketapp.dao;

import com.estockmarket.estockmarketapp.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyDao extends CrudRepository<Company, Integer> {

    Company findByCode(String code);
}
