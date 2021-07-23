package com.estockmarket.estockmarketapp.service;

//import com.estockmarket.estockmarketapp.Exception.CompanyNotFoundException;
import com.estockmarket.estockmarketapp.client.CompanyClient;
import com.estockmarket.estockmarketapp.common.StockRequest;
import com.estockmarket.estockmarketapp.common.StockResponse;
import com.estockmarket.estockmarketapp.common.TransactionRequest;
import com.estockmarket.estockmarketapp.common.TransactionResponse;
import com.estockmarket.estockmarketapp.dao.CompanyDao;
import com.estockmarket.estockmarketapp.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private CompanyClient companyClient;

    public TransactionResponse registerCompanyWithTransObject(TransactionRequest transactionRequest) {
        Company company = transactionRequest.getCompany();
        StockRequest stockRequest = transactionRequest.getStockRequest();
        // Rest call
        StockResponse stockResponse = companyClient.addStock(stockRequest, company.getCode());
        companyDao.save(company);
        return new TransactionResponse(company, stockResponse);
    }

    public Company getCompanyByCode(String code) {
        return companyDao.findByCode(code);
    }

    public List<Company> getAllCompanies() {
        return (List<Company>) companyDao.findAll();
    }

    public String deleteCompanyByCode(String code){
        Company company = companyDao.findByCode(code);
        if (company != null) {
            companyDao.delete(company);
            return "Company with code: " + code + " deleted.";
        } else throw new RuntimeException(code + " is not found!");
    }

    public Company getCompanyById(int id) {
        return  companyDao.findById(id).get();
    }

    public Company updateCompany(Company company) {
        return companyDao.save(company);
    }

    public List<StockResponse> getAllStocksResponse() {
        return companyClient.getAllStocks();
    }
}
