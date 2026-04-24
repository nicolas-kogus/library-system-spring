package com.github.nicolas_kogus.SystemLibrarySpring.Loan.controller;

import com.github.nicolas_kogus.SystemLibrarySpring.Loan.model.Loan;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping("/save-loan")
    public Loan createLoan(@RequestBody Loan loan) {return service.saveLoan(loan);}
}
