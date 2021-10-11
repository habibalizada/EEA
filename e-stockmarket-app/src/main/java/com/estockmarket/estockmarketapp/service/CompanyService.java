package com.estockmarket.estockmarketapp.service;

import com.estockmarket.estockmarketapp.exception.CompanyCollectionException;
import com.estockmarket.estockmarketapp.client.CompanyFeignClient;
import com.estockmarket.estockmarketapp.client.StockQueryFeignClient;
import com.estockmarket.estockmarketapp.common.Stock;
import com.estockmarket.estockmarketapp.common.StockRequest;
import com.estockmarket.estockmarketapp.common.TransactionRequest;
import com.estockmarket.estockmarketapp.common.TransactionResponse;
import com.estockmarket.estockmarketapp.dao.CompanyDao;
import com.estockmarket.estockmarketapp.model.Company;
import com.estockmarket.estockmarketapp.model.RequestCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private CompanyFeignClient companyFeignClient;

    @Autowired
    private StockQueryFeignClient stockQueryFeignClient;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;


    public void registerCompanyWithTransObject(TransactionRequest transactionRequest) throws ConstraintViolationException, CompanyCollectionException {
        Company company = transactionRequest.getCompany();
        StockRequest stockRequest = transactionRequest.getStockRequest();
        Optional<Company> optionalCompany = companyDao.findByCode(company.getCode());
        // To make sure company code is unique
        if (optionalCompany.isPresent()) {
            LOGGER.info("Company with given code already exists");
            throw new CompanyCollectionException(CompanyCollectionException.companyAlreadyExists());
        } else if (stockRequest.getStockPrice() == null) {
            LOGGER.info("Stock price can't be null");
            throw new CompanyCollectionException("Stock price can't be null");

        } else {
            company.setId(sequenceGeneratorService.generateSequence(Company.SEQUENCE_NAME));
            companyDao.save(company);
            LOGGER.info("Company saved");
            // Rest call
            companyFeignClient.addStock(stockRequest, company.getCode());
            LOGGER.info("Stock saved");

        }
    }

    public TransactionResponse getCompanyByCode(String code) throws CompanyCollectionException {
        Company company = companyDao.findByCode(code).orElseThrow(() -> new CompanyCollectionException(CompanyCollectionException.notFoundException(code)));
        Stock stock = stockQueryFeignClient.getLatestStockByCompanyCode(code);
        if (stock == null) {
            throw new CompanyCollectionException(CompanyCollectionException.stockNotFoundException(code));
        }
        LOGGER.info("Company with code: {} retrieved", code);
        return  new TransactionResponse(company, stock);
    }

    public List<TransactionResponse> getAllCompanies() {
        List<Company> companies = companyDao.findAll();
        Stock latestStock;
        List<TransactionResponse> transactionResponseList = new ArrayList<>();
        if (!companies.isEmpty()) {
            for (Company company : companies) {
                latestStock = stockQueryFeignClient.getLatestStockByCompanyCode(company.getCode());
                transactionResponseList.add(new TransactionResponse(company, latestStock));
            }
            LOGGER.info("All Companies with their latest Stock retrieved");
            return transactionResponseList;
        } else {
            return new ArrayList<>();
        }
    }

    public void deleteCompanyByCode(String code) throws CompanyCollectionException {
        Optional<Company> company = companyDao.findByCode(code);
        if (!company.isPresent()) {
            throw new CompanyCollectionException(CompanyCollectionException.notFoundException(code));
        } else {
            var stocks = stockQueryFeignClient.getStocksByCompanyCode(code);
            for (Stock s: stocks) {
                companyFeignClient.deleteStockById(s.getId());
            }
            LOGGER.info("Company with code: {} and it's all Stocks deleted", code);
            companyDao.delete(company.get());
        }
    }

    public Company getCompanyById(Long id) throws CompanyCollectionException {
        Optional<Company> company = companyDao.findById(id);
        if (!company.isPresent()) {
            throw new CompanyCollectionException(CompanyCollectionException.notFoundWithIdException(id));
        } else {
            return company.get();
        }
    }

    public Company updateCompany(RequestCompany requestCompany) throws CompanyCollectionException{
        Company company = new Company(requestCompany.getId(),requestCompany.getCode(),requestCompany.getName(),requestCompany.getCeo(),requestCompany.getTurnover(),requestCompany.getWebsite(),requestCompany.getEnlistedStockExchange());

        Optional<Company> oldCompany = companyDao.findById(company.getId());
        if (!oldCompany.isPresent()) {
            throw new CompanyCollectionException(CompanyCollectionException.notFoundWithIdException(company.getId()));
        }
        List<Stock> oldStoks = stockQueryFeignClient.getStocksByCompanyCode(oldCompany.get().getCode());
        for (Stock stock : oldStoks) {
            stock.setCompanyCode(company.getCode());
            companyFeignClient.updateStock(stock);
        }
        LOGGER.info("Company with code: {} and it's all Stocks updated", company.getCode());
        return companyDao.save(company);
    }

    public List<Stock> getAllStocksResponse() {
        return stockQueryFeignClient.getAllStocks();
    }
}
