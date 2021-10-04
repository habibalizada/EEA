package com.estockmarket.estockmarketapp.controller;

import com.estockmarket.estockmarketapp.exception.CompanyCollectionException;
import com.estockmarket.estockmarketapp.client.StockQueryFeignClient;
import com.estockmarket.estockmarketapp.common.Stock;
import com.estockmarket.estockmarketapp.common.TransactionRequest;
import com.estockmarket.estockmarketapp.common.TransactionResponse;
import com.estockmarket.estockmarketapp.model.Company;
import com.estockmarket.estockmarketapp.model.RequestCompany;
import com.estockmarket.estockmarketapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockQueryFeignClient stockQueryFeignClient;

    @PostMapping("/register")
    public ResponseEntity<String> registerCompany(@Valid @RequestBody TransactionRequest transactionRequest) {
        try {
            companyService.registerCompanyWithTransObject(transactionRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CompanyCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/info/{companycode}")
    public ResponseEntity<TransactionResponse> getCompanyByCode(@PathVariable String companycode) {
        try {
            TransactionResponse transactionResponse = companyService.getCompanyByCode(companycode);
            return new ResponseEntity<>(transactionResponse, HttpStatus.OK);

        } catch (CompanyCollectionException e) {
            return new ResponseEntity<>(new TransactionResponse(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{companycode}")
    public ResponseEntity<String> deleteCompanyByCode(@PathVariable String companycode) {
        try {
            companyService.deleteCompanyByCode(companycode);
            return new ResponseEntity<>("Successfully deleted company with code:" + companycode, HttpStatus.OK);
        } catch (CompanyCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<TransactionResponse>> getAllCompanies() {
        List<TransactionResponse> transactionResponseList = companyService.getAllCompanies();

        return new ResponseEntity<>(transactionResponseList, !transactionResponseList.isEmpty() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
        } catch (CompanyCollectionException e) {
            return new ResponseEntity<>(new Company(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCompany(@Valid @RequestBody RequestCompany requestCompany) {
        try {
            companyService.updateCompany(requestCompany);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CompanyCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/stocks")
    public List<Stock> getAllStocksResponse() {
        return companyService.getAllStocksResponse();
    }
}
