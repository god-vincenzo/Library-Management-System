package com.library.config;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.repository.UserRepository;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedBooks();
        seedMembers();
    }
    
    private void seedUsers() {
        if (userService.getAllUsers().isEmpty()) {
            userService.createUser("admin", "admin123", User.Role.ADMIN);
            userService.createUser("librarian", "lib123", User.Role.LIBRARIAN);
            System.out.println("✓ Seeded users: admin/admin123, librarian/lib123");
        }
    }
    
    private void seedBooks() {
        if (bookRepository.count() == 0) {
            Book[] books = {
                createBook("978-0134685991", "Effective Java", "Joshua Bloch", "Addison-Wesley", "Programming", 5, "A1-B2"),
                createBook("978-0596007126", "Head First Design Patterns", "Eric Freeman", "O'Reilly", "Design", 3, "A1-B3"),
                createBook("978-0132350884", "Clean Code", "Robert C. Martin", "Prentice Hall", "Programming", 4, "A1-B4"),
                createBook("978-0596009205", "Head First Java", "Kathy Sierra", "O'Reilly", "Programming", 6, "A1-B5"),
                createBook("978-0201633610", "Design Patterns", "Gang of Four", "Addison-Wesley", "Design", 3, "A1-B6"),
                createBook("978-0135974445", "The Pragmatic Programmer", "Andrew Hunt", "Addison-Wesley", "Programming", 4, "A2-B1"),
                createBook("978-0321125217", "Domain-Driven Design", "Eric Evans", "Addison-Wesley", "Architecture", 2, "A2-B2"),
                createBook("978-0137081073", "Refactoring", "Martin Fowler", "Addison-Wesley", "Programming", 5, "A2-B3"),
                createBook("978-1491950357", "Building Microservices", "Sam Newman", "O'Reilly", "Architecture", 3, "A2-B4"),
                createBook("978-0596007126", "Java Concurrency in Practice", "Brian Goetz", "Addison-Wesley", "Programming", 4, "A2-B5")
            };
            
            for (Book book : books) {
                bookRepository.save(book);
            }
            System.out.println("✓ Seeded 10 books");
        }
    }
    
    private void seedMembers() {
        if (memberRepository.count() == 0) {
            Member[] members = {
                createMember("MEM-001", "John Doe", "john.doe@email.com", "555-0101", "123 Main St", Member.Status.ACTIVE),
                createMember("MEM-002", "Jane Smith", "jane.smith@email.com", "555-0102", "456 Oak Ave", Member.Status.ACTIVE),
                createMember("MEM-003", "Bob Johnson", "bob.johnson@email.com", "555-0103", "789 Pine Rd", Member.Status.ACTIVE),
                createMember("MEM-004", "Alice Williams", "alice.williams@email.com", "555-0104", "321 Elm St", Member.Status.ACTIVE),
                createMember("MEM-005", "Charlie Brown", "charlie.brown@email.com", "555-0105", "654 Maple Dr", Member.Status.ACTIVE)
            };
            
            for (Member member : members) {
                memberRepository.save(member);
            }
            System.out.println("✓ Seeded 5 members");
        }
    }
    
    private Book createBook(String isbn, String title, String author, String publisher, String category, int quantity, String shelfLocation) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setCategory(category);
        book.setQuantity(quantity);
        book.setAvailableQuantity(quantity);
        book.setShelfLocation(shelfLocation);
        return book;
    }
    
    private Member createMember(String memberId, String name, String email, String phone, String address, Member.Status status) {
        Member member = new Member();
        member.setMemberId(memberId);
        member.setName(name);
        member.setEmail(email);
        member.setPhone(phone);
        member.setAddress(address);
        member.setStatus(status);
        return member;
    }
}

