package com.cts.CompanyManagementApp.repository;

import com.cts.CompanyManagementApp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CompanyRepo extends JpaRepository<Company, Long> {
}
