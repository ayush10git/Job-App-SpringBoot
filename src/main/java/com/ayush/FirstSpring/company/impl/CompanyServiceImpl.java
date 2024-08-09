package com.ayush.FirstSpring.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ayush.FirstSpring.company.Company;
import com.ayush.FirstSpring.company.CompanyRepository;
import com.ayush.FirstSpring.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()) {
            Company updatedCompany = companyOptional.get();
            updatedCompany.setDescription(company.getDescription());
            updatedCompany.setName(company.getName());
            updatedCompany.setJobs(company.getJobs());
            companyRepository.save(updatedCompany);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        try {
            companyRepository.deleteById(id); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
