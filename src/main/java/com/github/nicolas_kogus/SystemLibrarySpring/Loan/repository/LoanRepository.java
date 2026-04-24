package com.github.nicolas_kogus.SystemLibrarySpring.Loan.repository;

import com.github.nicolas_kogus.SystemLibrarySpring.Loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
