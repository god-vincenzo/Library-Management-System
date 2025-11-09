package com.library.service;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Value("${library.issue.days-due:14}")
    private int daysDue;
    
    @Value("${library.fine.amount-per-day:1.0}")
    private double fineAmountPerDay;
    
    public Transaction issueBook(Long bookId, Long memberId) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Book not found"));
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("Member not found"));
        
        if (member.getStatus() != Member.Status.ACTIVE) {
            throw new RuntimeException("Member is not active");
        }
        
        if (book.getAvailableQuantity() <= 0) {
            throw new RuntimeException("Book is not available");
        }
        
        Transaction transaction = new Transaction();
        transaction.setBook(book);
        transaction.setMember(member);
        transaction.setIssueDate(LocalDate.now());
        transaction.setDueDate(LocalDate.now().plusDays(daysDue));
        transaction.setStatus(Transaction.TransactionStatus.ISSUED);
        transaction.setFinePaid(false);
        transaction.setFineAmount(BigDecimal.ZERO);
        
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        bookRepository.save(book);
        
        return transactionRepository.save(transaction);
    }
    
    public Transaction returnBook(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
        
        if (transaction.getStatus() == Transaction.TransactionStatus.RETURNED) {
            throw new RuntimeException("Book already returned");
        }
        
        transaction.setReturnDate(LocalDate.now());
        transaction.setStatus(Transaction.TransactionStatus.RETURNED);
        
        // Calculate fine if overdue
        if (LocalDate.now().isAfter(transaction.getDueDate())) {
            long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(
                transaction.getDueDate(), LocalDate.now());
            BigDecimal fine = BigDecimal.valueOf(daysOverdue * fineAmountPerDay);
            transaction.setFineAmount(fine);
        }
        
        // Update book availability
        Book book = transaction.getBook();
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);
        bookRepository.save(book);
        
        return transactionRepository.save(transaction);
    }
    
    public void payFine(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setFinePaid(true);
        transactionRepository.save(transaction);
    }
    
    public List<Transaction> getMemberTransactions(Long memberId) {
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("Member not found"));
        return transactionRepository.findByMember(member);
    }
    
    public List<Transaction> getOverdueTransactions() {
        return transactionRepository.findOverdueTransactions(LocalDate.now());
    }
    
    public List<Transaction> getActiveTransactions() {
        return transactionRepository.findByStatus(Transaction.TransactionStatus.ISSUED);
    }
    
    public BigDecimal calculateFine(Long transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new RuntimeException("Transaction not found"));
        
        if (transaction.getReturnDate() != null || 
            LocalDate.now().isBefore(transaction.getDueDate())) {
            return BigDecimal.ZERO;
        }
        
        long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(
            transaction.getDueDate(), LocalDate.now());
        return BigDecimal.valueOf(daysOverdue * fineAmountPerDay);
    }
}

