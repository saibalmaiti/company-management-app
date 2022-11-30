package com.cts.CompanyManagementApp.service;

import com.cts.CompanyManagementApp.exception.DuplicateCompanyCodeException;
import com.cts.CompanyManagementApp.model.Company;
import com.cts.CompanyManagementApp.model.ExJWT;
import com.cts.CompanyManagementApp.repository.CompanyRepo;
import com.cts.CompanyManagementApp.repository.ExJWTRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private ExJWTRepo exJWTRepo;

    @Override
    public Company addCompany(Company company) throws DuplicateCompanyCodeException {
        if(companyRepo.findById(company.getCompanyCode()).isPresent()) {
            throw new DuplicateCompanyCodeException();
        }
        return companyRepo.save(company);
    }

    @Override
    public List<Company> getAllCompany() {
        List<Company> companyDetails = companyRepo.findAll();
        return companyDetails;
    }

    @Override
    public Company getCompanyByCode(long companyCode) {
        Optional<Company> optionalCompany = companyRepo.findCompanyByCompanyCode(companyCode);
        if(optionalCompany.isPresent())
            return optionalCompany.get();
        return null;
    }

    @Override
    public boolean updateCompany(Company company) {
        Company companyObj = getCompanyByCode(company.getCompanyCode());
        if(companyObj != null) {
            companyObj.setCompanyCeo(company.getCompanyCeo());
            companyObj.setCompanyName(company.getCompanyName());
            companyObj.setCompanyTurnover(company.getCompanyTurnover());
            companyObj.setCompanyWebSite(company.getCompanyWebSite());
            companyObj.setStockExchange(company.getStockExchange());
//            log.info(companyObj.toString());
            companyRepo.save(companyObj);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(long companyCode) {
        companyRepo.deleteById(companyCode);
        return true;
    }

    @Override
    public ExJWT getExpiredToken(String tokenId) {
        Optional<ExJWT> exJWTOptional = exJWTRepo.findById(tokenId);
        if(exJWTOptional.isPresent())
            return exJWTOptional.get();
        return null;
    }


}
