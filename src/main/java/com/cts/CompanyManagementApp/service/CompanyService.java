package com.cts.CompanyManagementApp.service;

import com.cts.CompanyManagementApp.exception.DuplicateCompanyCodeException;
import com.cts.CompanyManagementApp.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    public Company addCompany(Company company) throws DuplicateCompanyCodeException;
    public List<Company> getAllCompany();
    public Company getCompanyByCode(long companyCode);
    public boolean updateCompany(Company company);
    public boolean deleteCompany(long companyCode);
}
