package com.library.repository;

import com.library.model.Transaction;
import com.library.model.Transaction.TransactionStatus;
import com.library.model.Book;
import com.library.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByMember(Member member);
    List<Transaction> findByBook(Book book);
    List<Transaction> findByStatus(TransactionStatus status);
    List<Transaction> findByDueDateBeforeAndStatus(LocalDate date, TransactionStatus status);
    
    @Query("SELECT t FROM Transaction t WHERE t.member = :member AND t.status = 'ISSUED'")
    List<Transaction> findActiveTransactionsByMember(@Param("member") Member member);
    
    @Query("SELECT t FROM Transaction t WHERE t.dueDate < :date AND t.status = 'ISSUED'")
    List<Transaction> findOverdueTransactions(@Param("date") LocalDate date);
    
    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.book = :book AND t.status = 'ISSUED'")
    Long countIssuedCopies(@Param("book") Book book);
}

