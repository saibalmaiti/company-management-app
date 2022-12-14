package com.cts.CompanyManagementApp;

import com.cts.CompanyManagementApp.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CompanyManagementAppApplication {
	@Autowired
	private JwtFilter jwtFilter;
	@Bean
	public FilterRegistrationBean jwtFilterBean() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(jwtFilter);
		frb.addUrlPatterns("/api/v1.0/market/company/*");
		frb.addUrlPatterns("/api/v1.0/market/stock/*");
		frb.addUrlPatterns("/auth/v1/user/logout");
		return frb;
	}
	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementAppApplication.class, args);
	}

}
