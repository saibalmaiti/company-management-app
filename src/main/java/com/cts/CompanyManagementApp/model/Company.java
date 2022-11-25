package com.cts.CompanyManagementApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    private Long companyCode;
    @NotBlank(message = "company name is mandatory")
    private String companyName;
    @NotBlank(message = "company ceo is mandatory")
    private String companyCeo;
    @Min(value = 10, message = "company turnover must be greater than 10cr")
    private double companyTurnover;
    @NotBlank(message = "company website is mandatory")
    private String companyWebSite;
    @NotBlank(message = "stock exchange is mandatory")
    private String stockExchange;
    private double stockPrice;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Company.class)
    @JsonIgnore
    private List<Stock> stockList;
}
