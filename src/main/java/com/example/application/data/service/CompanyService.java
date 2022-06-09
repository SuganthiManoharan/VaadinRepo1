package com.example.application.data.service;


import com.example.application.data.entity.Company;
import com.example.application.data.repository.CompanyRepository;
import com.example.application.data.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CompanyService {
    private static final Logger LOGGER = Logger.getLogger(CompanyService.class.getName());

    private CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Map<String, Integer> getStats() {
        HashMap<String, Integer> stats = new HashMap<>();
        findAll().forEach(company ->
                stats.put(company.getName(), company.getEmployees().size()));
        return stats;
    }

    public List<Company> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return companyRepository.findAll();
        } else  {
            return  companyRepository.search(filterText);
        }
    }

    public void save(Company company) {
        if (company == null) {
            LOGGER.log(Level.SEVERE,
                    "company is null. Are you sure you have connected your form to the application?");
            return;
        }

        companyRepository.save(company);
    }
}
