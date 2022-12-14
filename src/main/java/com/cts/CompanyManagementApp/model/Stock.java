package com.cts.CompanyManagementApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    @GeneratedValue
    private long transactionId;
    @CreationTimestamp
    private Date updatedAt;
    private double stockPrice;
//    private long company_code_fk;

}
