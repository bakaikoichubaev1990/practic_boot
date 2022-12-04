package com.example.practic_boot.service.impl;

import com.example.practic_boot.model.Company;
import com.example.practic_boot.repository.CompanyRepository;
import com.example.practic_boot.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);

    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.getById(id);
    }

    @Override
    public void updateCompany(Company company) {
        companyRepository.save(company);

    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);

    }
}
