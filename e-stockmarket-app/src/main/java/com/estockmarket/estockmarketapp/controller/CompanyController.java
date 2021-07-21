package com.estockmarket.estockmarketapp.controller;

import com.estockmarket.estockmarketapp.dao.CompanyDao;
import com.estockmarket.estockmarketapp.model.Company;
import com.estockmarket.estockmarketapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/market/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/register")
    public Company registerCompany(@Valid @RequestBody Company company) {
        return companyService.registerCompany(company);
    }

    @GetMapping("/info/{companycode}")
    public Company getCompanyByCode(@PathVariable String companycode) {
        return companyService.getCompanyByCode(companycode);
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
}
