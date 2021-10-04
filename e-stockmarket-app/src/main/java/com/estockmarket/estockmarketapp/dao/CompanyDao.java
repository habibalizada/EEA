package com.estockmarket.estockmarketapp.dao;

import com.estockmarket.estockmarketapp.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyDao extends MongoRepository<Company, Long> {

    @Query("{'code': ?0}")
    Optional<Company> findByCode(String code);
}
