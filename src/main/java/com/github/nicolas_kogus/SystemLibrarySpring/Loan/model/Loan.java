package com.github.nicolas_kogus.SystemLibrarySpring.Loan.model;

import jakarta.persistence.*;

/**
 * Representa a entidade de Empréstimo (Loan) no sistema da biblioteca.
 * Esta classe é mapeada para a tabela 'tb_loan' no banco de dados.
 */
@Entity
@Table(name = "tb_loan")
public class Loan {

    /**
     * Identificador único do empréstimo.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * O ID do livro que está sendo emprestado.
     */
    private Long bookId;

    /**
     * O ID do usuário que está realizando o empréstimo.
     */
    private Long userId;

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public Loan() {
    }

    /**
     * Construtor para criar um novo empréstimo com os dados iniciais.
     *
     * @param bookId Identificador do livro.
     * @param userId Identificador do usuário.
     */
    public Loan(Long bookId, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    /**
     * Obtém o ID do empréstimo.
     * @return O ID do empréstimo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID do empréstimo.
     * @param id O ID a ser definido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o ID do livro associado a este empréstimo.
     * @return O ID do livro.
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * Define o ID do livro para este empréstimo.
     * @param bookId O ID do livro.
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * Obtém o ID do usuário associado a este empréstimo.
     * @return O ID do usuário.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Define o ID do usuário para este empréstimo.
     * @param userId O ID do usuário.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Retorna uma representação em String do objeto Loan.
     * Útil para fins de log e depuração.
     *
     * @return String contendo os dados do empréstimo.
     */
    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                '}';
    }
}
