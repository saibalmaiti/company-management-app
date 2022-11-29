package com.cts.CompanyManagementApp.controller;

import com.cts.CompanyManagementApp.model.Company;
import com.cts.CompanyManagementApp.service.CompanyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerTests {
    @Mock
    private CompanyServiceImpl companyService;
    @InjectMocks
    private CompanyController companyController;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
    }
    private List<Company> companyList = new ArrayList<>();
    @Test
    public void getAllCompany() throws Exception {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        companyList.add(company);
        when(companyService.getAllCompany()).thenReturn(companyList);
        List<Company> cList = companyService.getAllCompany();
        assertEquals(companyList, cList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/getall").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void addCompanySuccess() throws Exception {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        when(companyService.addCompany(any())).thenReturn(company);
        Company company1 = companyService.addCompany(company);
        assertEquals(company, company1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(company))).andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void getCompanyByIdSuccess() throws Exception {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        when(companyService.getCompanyByCode(company.getCompanyCode())).thenReturn(company);
        assertEquals(company, companyService.getCompanyByCode(company.getCompanyCode()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/info/101").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void updateCompanySuccess() throws Exception {
        Company company = new Company();
        company.setCompanyCode(101L);
        company.setCompanyName("Cognizant");
        company.setCompanyCeo("B.H");
        company.setCompanyTurnover(11.4);
        company.setCompanyWebSite("www.cognizant.com");
        company.setStockExchange("NSE");
        when(companyService.updateCompany(company)).thenReturn(true);
        assertTrue(companyService.updateCompany(company));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1.0/market/company/put/101").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(company))).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
