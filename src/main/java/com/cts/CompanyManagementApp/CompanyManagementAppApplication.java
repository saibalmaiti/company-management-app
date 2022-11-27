package com.cts.CompanyManagementApp;

import com.cts.CompanyManagementApp.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CompanyManagementAppApplication {
	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new JwtFilter());
		frb.addUrlPatterns("/api/v1.0/market/company/*");
		frb.addUrlPatterns("/api/v1.0/market/stock/*");
		return frb;
	}
	public static void main(String[] args) {
		SpringApplication.run(CompanyManagementAppApplication.class, args);
	}

}
