package com.estockmarket.estockmarketapp.service;

//import com.estockmarket.estockmarketapp.Exception.CompanyNotFoundException;
import com.estockmarket.estockmarketapp.dao.CompanyDao;
import com.estockmarket.estockmarketapp.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    public Company registerCompany(Company company) {
        return companyDao.save(company);
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
//        return companyDao.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
//        return  (Optional)companyDao.findById(id);
        return  companyDao.findById(id).get();
    }

    public Company updateCompany(Company company) {
        return companyDao.save(company);
    }

    public List<Company> registerCompanies(List<Company> companies) {
        return (List<Company>) companyDao.saveAll(companies);
    }
}
