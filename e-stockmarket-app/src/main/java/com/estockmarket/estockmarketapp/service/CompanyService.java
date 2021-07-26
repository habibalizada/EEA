package com.estockmarket.estockmarketapp.service;

//import com.estockmarket.estockmarketapp.Exception.CompanyNotFoundException;
import com.estockmarket.estockmarketapp.Exception.CompanyCollectionException;
import com.estockmarket.estockmarketapp.client.CompanyClient;
import com.estockmarket.estockmarketapp.common.StockRequest;
import com.estockmarket.estockmarketapp.common.StockResponse;
import com.estockmarket.estockmarketapp.common.TransactionRequest;
import com.estockmarket.estockmarketapp.common.TransactionResponse;
import com.estockmarket.estockmarketapp.dao.CompanyDao;
import com.estockmarket.estockmarketapp.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    public TransactionResponse registerCompanyWithTransObject(TransactionRequest transactionRequest) throws ConstraintViolationException, CompanyCollectionException{
        Company company = transactionRequest.getCompany();
        StockRequest stockRequest = transactionRequest.getStockRequest();
        StockResponse stockResponse = null;
        Optional<Company> optionalCompany = companyDao.findByCode(company.getCode());
        if (optionalCompany.isPresent()) {
            throw new CompanyCollectionException(CompanyCollectionException.CompanyAlreadyExists());
        } else {
            company.setId(sequenceGeneratorService.generateSequence(Company.SEQUENCE_NAME));
            companyDao.save(company);
            // Rest call
            stockResponse = companyClient.addStock(stockRequest, company.getCode());

        }
        return new TransactionResponse(company, stockResponse);
    }

    public ResponseEntity<Company> getCompanyByCode(String code) throws CompanyCollectionException {
        Company company = companyDao.findByCode(code).orElseThrow(()-> new CompanyCollectionException(CompanyCollectionException.NotFoundException(code)));
//        return companyDao.findByCode(code);
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

    public List<StockResponse> getAllStocksResponse() {
        return companyClient.getAllStocks();
    }
}
