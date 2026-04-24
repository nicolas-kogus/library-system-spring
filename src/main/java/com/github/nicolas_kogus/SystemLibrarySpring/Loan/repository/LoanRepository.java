package com.github.nicolas_kogus.SystemLibrarySpring.Loan.repository;

import com.github.nicolas_kogus.SystemLibrarySpring.Loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório para a entidade {@link Loan}.
 * Fornece a abstração necessária para realizar operações de persistência, 
 * consulta e deleção de registros de empréstimos no banco de dados.
 */
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
