package com.estockmarket.estockmarketapp.controller;

import com.estockmarket.estockmarketapp.Exception.CompanyCollectionException;
import com.estockmarket.estockmarketapp.common.StockResponse;
import com.estockmarket.estockmarketapp.common.TransactionRequest;
import com.estockmarket.estockmarketapp.common.TransactionResponse;
import com.estockmarket.estockmarketapp.model.Company;
import com.estockmarket.estockmarketapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

//    @PostMapping("/register")
//    public Company registerCompany(@Valid @RequestBody Company company) {
//        return companyService.registerCompany(company);
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody TransactionRequest transactionRequest) {
        try {
            companyService.registerCompanyWithTransObject(transactionRequest);
            return new ResponseEntity<>(transactionRequest, HttpStatus.OK);

        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (CompanyCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/info/{companycode}")
    public ResponseEntity<?> getCompanyByCode(@PathVariable String companycode) {
        try {
            return companyService.getCompanyByCode(companycode);
        } catch (CompanyCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{companycode}")
    public String deleteCompanyByCode(@PathVariable String companycode) {
        return companyService.deleteCompanyByCode(companycode);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return new ResponseEntity<>(companies, companies.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id) {
        try {
            return companyService.getCompanyById(id);
        } catch (CompanyCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public Company updateCompany(@Valid @RequestBody Company company) {
        return companyService.updateCompany(company);
    }

    @GetMapping("/stocks")
    public List<StockResponse> getAllStocksResponse() {
        return  companyService.getAllStocksResponse();
    }
}
