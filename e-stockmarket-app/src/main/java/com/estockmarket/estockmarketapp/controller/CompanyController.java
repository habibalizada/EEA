package com.estockmarket.estockmarketapp.controller;

import com.estockmarket.estockmarketapp.common.StockResponse;
import com.estockmarket.estockmarketapp.common.TransactionRequest;
import com.estockmarket.estockmarketapp.common.TransactionResponse;
import com.estockmarket.estockmarketapp.model.Company;
import com.estockmarket.estockmarketapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public TransactionResponse registerCompany(@Valid @RequestBody TransactionRequest transactionRequest) {
        return companyService.registerCompanyWithTransObject(transactionRequest);
    }

    @GetMapping("/info/{companycode}")
    public Company getCompanyByCode(@PathVariable String companycode) {
        return companyService.getCompanyByCode(companycode);
        // Do a rest call to Stock API and pass the company ID
    }

    @DeleteMapping("/delete/{companycode}")
    public String deleteCompanyByCode(@PathVariable String companycode) {
        return companyService.deleteCompanyByCode(companycode);
    }

    @GetMapping("/getall")
    public List<Company> getAllCompanies() {
        return (List<Company>) companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
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
