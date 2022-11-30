package com.cts.CompanyManagementApp.controller;

import com.cts.CompanyManagementApp.exception.DuplicateCompanyCodeException;
import com.cts.CompanyManagementApp.model.Company;
import com.cts.CompanyManagementApp.response.ResponseHandler;
import com.cts.CompanyManagementApp.service.CompanyService;
import com.cts.CompanyManagementApp.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1.0/market/company")
@Slf4j
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private StockService stockService;
    CacheControl cacheControl = CacheControl.maxAge(2, TimeUnit.MINUTES);
    @PostMapping("/register")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody Company company)throws DuplicateCompanyCodeException {
        log.info(company.toString());
        if(companyService.addCompany(company)!=null)
            return ResponseEntity.status(HttpStatus.CREATED).body("Company Registered Successfully");
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Couldn't Register Company");
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAllCompany() {
        return ResponseEntity.ok().cacheControl(cacheControl)
                .body(ResponseHandler.generateResponse("Successfully fetching the data",HttpStatus.OK,companyService.getAllCompany()));
//        return ResponseEntity.ok().body(companyService.getAllCompany());
    }
    @GetMapping("/info/{companycode}")
    public ResponseEntity<?> getAllCompanyByCode(@PathVariable(name = "companycode") long companyCode) {
        return ResponseEntity.ok().cacheControl(cacheControl)
                .body(ResponseHandler.generateResponse("Successfully fetching the data",HttpStatus.OK,companyService.getCompanyByCode(companyCode)));
//        return ResponseEntity.ok().body(companyService.getCompanyByCode(companyCode));
    }
    @PutMapping("/put/{companycode}")
    public ResponseEntity<?> updateCompanyByCode(@PathVariable(name="companycode") long companyCode, @RequestBody Company company) {
        if(companyService.updateCompany(company))
            return ResponseEntity.ok().body("Company Details Updated Successfully");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to Update");
    }
    @DeleteMapping("/delete/{companycode}")
    public ResponseEntity<?> deleteCompanyByCode(@PathVariable(name ="companycode") long companyCode) {
        if ( companyService.deleteCompany(companyCode))
            return ResponseEntity.ok().body("Company Deleted Successfully");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Company can't be deleted");
    }
}
