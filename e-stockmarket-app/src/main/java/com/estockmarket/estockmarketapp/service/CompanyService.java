package com.estockmarket.estockmarketapp.service;

import com.estockmarket.estockmarketapp.Exception.CompanyCollectionException;
import com.estockmarket.estockmarketapp.client.CompanyFeignClient;
import com.estockmarket.estockmarketapp.common.StockRequest;
import com.estockmarket.estockmarketapp.common.Stock;
import com.estockmarket.estockmarketapp.common.TransactionRequest;
import com.estockmarket.estockmarketapp.common.TransactionResponse;
import com.estockmarket.estockmarketapp.dao.CompanyDao;
import com.estockmarket.estockmarketapp.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    public TransactionResponse registerCompanyWithTransObject(TransactionRequest transactionRequest) throws ConstraintViolationException, CompanyCollectionException{
        Company company = transactionRequest.getCompany();
        StockRequest stockRequest = transactionRequest.getStockRequest();
        Stock stock = null;
        Optional<Company> optionalCompany = companyDao.findByCode(company.getCode());
        // To make sure company code is unique
        if (optionalCompany.isPresent()) {
            throw new CompanyCollectionException(CompanyCollectionException.CompanyAlreadyExists());
        } else if (stockRequest.getStockPrice() == null) {
            throw new CompanyCollectionException("Stock price can't be null");

        } else {
            company.setId(sequenceGeneratorService.generateSequence(Company.SEQUENCE_NAME));
            companyDao.save(company);
            // Rest call
            stock = companyFeignClient.addStock(stockRequest, company.getCode());

        }
        return new TransactionResponse(company, stock);
    }

    public ResponseEntity<Company> getCompanyByCode(String code) throws CompanyCollectionException {
        Company company = companyDao.findByCode(code).orElseThrow(()-> new CompanyCollectionException(CompanyCollectionException.NotFoundException(code)));
        return ResponseEntity.ok().body(company);
    }

    public List<Company> getAllCompanies() {
        List<Company> companies = companyDao.findAll();
        if (companies.size() > 0) {
            return companies;
        } else {
            return new ArrayList<Company>();
        }
    }

    public void deleteCompanyByCode(String code) throws CompanyCollectionException{
        Optional<Company> company = companyDao.findByCode(code);
        if (!company.isPresent()) {
            throw new CompanyCollectionException(CompanyCollectionException.NotFoundException(code));
        } else {
            companyDao.delete(company.get());
            companyFeignClient.deleteStockByCompanyCode(code);
        }
    }

    public Company getCompanyById(Long id) throws CompanyCollectionException {
        Optional<Company> company = companyDao.findById(id);
        if (!company.isPresent()) {
            throw new CompanyCollectionException(CompanyCollectionException.NotFoundWithIdException(id));
        } else {
            return company.get();
        }
    }

    public Company updateCompany(Company company) {
        return companyDao.save(company);
    }

    public List<Stock> getAllStocksResponse() {
        return companyFeignClient.getAllStocks();
    }
}
