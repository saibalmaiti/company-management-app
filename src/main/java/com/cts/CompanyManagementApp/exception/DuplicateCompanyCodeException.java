package com.cts.CompanyManagementApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Company code already exists")
public class DuplicateCompanyCodeException extends Exception{
    public DuplicateCompanyCodeException() {
        super();
    }
}
