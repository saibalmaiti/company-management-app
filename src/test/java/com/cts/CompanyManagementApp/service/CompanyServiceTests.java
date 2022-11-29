package com.cts.CompanyManagementApp.service;

import com.cts.CompanyManagementApp.exception.DuplicateCompanyCodeException;
import com.cts.CompanyManagementApp.model.Company;
import com.cts.CompanyManagementApp.repository.CompanyRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyServiceTests {
    @Mock
    private CompanyRepo companyRepo;
    @InjectMocks
    private CompanyServiceImpl companyService;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companyService).build();
    }
    @Test
    public void addCompanySuccess() throws DuplicateCompanyCodeException {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        when(companyRepo.save(company)).thenReturn(company);
        Company company1 = companyService.addCompany(company);
        assertEquals(company, company1);
    }
    @Test
    public void addCompanyFailure() throws DuplicateCompanyCodeException {
        Company company = new Company();
        when(companyRepo.save(any())).thenReturn(null);
        Company company1 = companyService.addCompany(company);
        assertNull(company1);
    }
    @Test
    public void getAllCompanySuccess() {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        List<Company> companyList = new ArrayList<>();
        companyList.add(company);
        when(companyRepo.findAll()).thenReturn(companyList);
        assertEquals(companyList.size(), companyService.getAllCompany().size());
    }
    @Test
    public void getCompanyCodeSuccess() {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        Optional<Company> optionalCompany = Optional.of(company);
        when(companyRepo.findCompanyByCompanyCode(company.getCompanyCode())).thenReturn(optionalCompany);
        assertEquals(company, companyService.getCompanyByCode(company.getCompanyCode()));
    }
    @Test
    public void getCompanyCodeFailure() {
        Company company = new Company();
        company.setCompanyCode(101L);
        when(companyRepo.findCompanyByCompanyCode(anyLong())).thenReturn(Optional.empty());
        assertNull(companyService.getCompanyByCode(company.getCompanyCode()));
    }
    @Test
    public void updateCompanySuccess() {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        when(companyRepo.findCompanyByCompanyCode(company.getCompanyCode())).thenReturn(Optional.of(company));
        assertTrue(companyService.updateCompany(company));
    }
    @Test
    public void updateCompanyFailure() {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        when(companyRepo.findCompanyByCompanyCode(anyLong())).thenReturn(Optional.empty());
        assertFalse(companyService.updateCompany(company));
    }
}
