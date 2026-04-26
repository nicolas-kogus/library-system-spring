package com.github.nicolas_kogus.SystemLibrarySpring.Loan.controller;

import com.github.nicolas_kogus.SystemLibrarySpring.Loan.model.Loan;
import com.github.nicolas_kogus.SystemLibrarySpring.Loan.service.LoanService;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller that handles HTTP requests related to Loan operations.
 * Provides endpoints to record and manage book loans.
 */
@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService service;

    /**
     * Constructor for LoanController.
     * Uses constructor-based dependency injection to ensure the service is available and immutable.
     *
     * @param service The LoanService instance used to process loan logic.
     */
    public LoanController(LoanService service) {
        this.service = service;
    }

    /**
     * Endpoint to register a new loan in the system.
     * 
     * @param loan The loan details received in the request body.
     * @return The saved Loan entity returned by the service layer.
     */
    @PostMapping("/save-loan")
    public Loan createLoan(@RequestBody Loan loan) {
        return service.saveLoan(loan);
    }

    @DeleteMapping("delete-loan/{id}")
    public void deleteLoanById(@PathVariable(name = "id") Long id) {service.deleteLoanById(id);}
}
