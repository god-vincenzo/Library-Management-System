package com.library.ui;

import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.service.TransactionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class DashboardController implements Initializable {
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private StageManager stageManager;
    
    // Statistics Labels
    @FXML private Label totalBooksLabel;
    @FXML private Label availableBooksLabel;
    @FXML private Label totalMembersLabel;
    @FXML private Label issuedBooksLabel;
    @FXML private Label overdueBooksLabel;
    
    // Books Table
    @FXML private TableView<Book> booksTable;
    @FXML private TableColumn<Book, Long> bookIdColumn;
    @FXML private TableColumn<Book, String> isbnColumn;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, String> categoryColumn;
    @FXML private TableColumn<Book, Integer> quantityColumn;
    @FXML private TableColumn<Book, Integer> availableColumn;
    @FXML private TextField bookSearchField;
    
    // Members Table
    @FXML private TableView<Member> membersTable;
    @FXML private TableColumn<Member, String> memberIdColumn;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> emailColumn;
    @FXML private TableColumn<Member, String> phoneColumn;
    @FXML private TableColumn<Member, String> statusColumn;
    @FXML private TextField memberSearchField;
    
    // Transactions Table
    @FXML private TableView<Transaction> transactionsTable;
    @FXML private TableColumn<Transaction, Long> transactionIdColumn;
    @FXML private TableColumn<Transaction, String> transactionBookColumn;
    @FXML private TableColumn<Transaction, String> transactionMemberColumn;
    @FXML private TableColumn<Transaction, LocalDate> issueDateColumn;
    @FXML private TableColumn<Transaction, LocalDate> dueDateColumn;
    @FXML private TableColumn<Transaction, String> transactionStatusColumn;
    
    // Issue/Return Controls
    @FXML private TextField issueBookIdField;
    @FXML private TextField issueMemberIdField;
    @FXML private TextField returnTransactionIdField;
    
    // Book Management
    @FXML private TextField addIsbnField;
    @FXML private TextField addTitleField;
    @FXML private TextField addAuthorField;
    @FXML private TextField addPublisherField;
    @FXML private TextField addCategoryField;
    @FXML private TextField addQuantityField;
    @FXML private TextField addShelfField;
    @FXML private Button addBookButton;
    
    // Member Management
    @FXML private TextField addMemberIdField;
    @FXML private TextField addMemberNameField;
    @FXML private TextField addMemberEmailField;
    @FXML private TextField addMemberPhoneField;
    @FXML private TextField addMemberAddressField;
    
    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    private ObservableList<Member> memberList = FXCollections.observableArrayList();
    private ObservableList<Transaction> transactionList = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupBooksTable();
        setupMembersTable();
        setupTransactionsTable();
        loadData();
        updateStatistics();
    }
    
    private void setupBooksTable() {
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("availableQuantity"));
        booksTable.setItems(bookList);
    }
    
    private void setupMembersTable() {
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        membersTable.setItems(memberList);
    }
    
    private void setupTransactionsTable() {
        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        transactionBookColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getBook().getTitle()));
        transactionMemberColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMember().getName()));
        issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        transactionStatusColumn.setCellValueFactory(cellData -> 
            new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus().toString()));
        transactionsTable.setItems(transactionList);
    }
    
    private void loadData() {
        bookList.clear();
        bookList.addAll(bookService.getAllBooks());
        
        memberList.clear();
        memberList.addAll(memberService.getAllMembers());
        
        transactionList.clear();
        transactionList.addAll(transactionService.getActiveTransactions());
    }
    
    private void updateStatistics() {
        totalBooksLabel.setText(String.valueOf(bookService.getAllBooks().size()));
        availableBooksLabel.setText(String.valueOf(bookService.getAvailableBooks().size()));
        totalMembersLabel.setText(String.valueOf(memberService.getAllMembers().size()));
        issuedBooksLabel.setText(String.valueOf(transactionService.getActiveTransactions().size()));
        overdueBooksLabel.setText(String.valueOf(transactionService.getOverdueTransactions().size()));
    }
    
    @FXML
    private void handleBookSearch() {
        String query = bookSearchField.getText();
        if (query.isEmpty()) {
            loadData();
        } else {
            bookList.clear();
            bookList.addAll(bookService.searchBooks(query));
        }
    }
    
    @FXML
    private void handleMemberSearch() {
        String query = memberSearchField.getText();
        if (query.isEmpty()) {
            memberList.clear();
            memberList.addAll(memberService.getAllMembers());
        } else {
            memberList.clear();
            memberList.addAll(memberService.searchMembers(query));
        }
    }
    
    @FXML
    private void handleIssueBook() {
        try {
            Long bookId = Long.parseLong(issueBookIdField.getText());
            Long memberId = Long.parseLong(issueMemberIdField.getText());
            
            Transaction transaction = transactionService.issueBook(bookId, memberId);
            showAlert(Alert.AlertType.INFORMATION, "Success", 
                "Book issued successfully! Transaction ID: " + transaction.getId());
            
            loadData();
            updateStatistics();
            issueBookIdField.clear();
            issueMemberIdField.clear();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
    
    @FXML
    private void handleReturnBook() {
        try {
            Long transactionId = Long.parseLong(returnTransactionIdField.getText());
            Transaction transaction = transactionService.returnBook(transactionId);
            
            BigDecimal fine = transaction.getFineAmount();
            String message = "Book returned successfully!";
            if (fine.compareTo(BigDecimal.ZERO) > 0) {
                message += " Fine: $" + fine;
            }
            
            showAlert(Alert.AlertType.INFORMATION, "Success", message);
            loadData();
            updateStatistics();
            returnTransactionIdField.clear();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
    
    @FXML
    private void handleAddBook() {
        System.out.println("handleAddBook() called");
        
        // Disable button during submission to prevent double-clicks
        if (addBookButton != null) {
            addBookButton.setDisable(true);
            addBookButton.setText("Adding...");
        }
        
        try {
            // Validate required fields
            if (addIsbnField.getText() == null || addIsbnField.getText().trim().isEmpty()) {
                if (addBookButton != null) {
                    addBookButton.setDisable(false);
                    addBookButton.setText("ADD BOOK");
                }
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter ISBN");
                addIsbnField.requestFocus();
                return;
            }
            
            if (addTitleField.getText() == null || addTitleField.getText().trim().isEmpty()) {
                if (addBookButton != null) {
                    addBookButton.setDisable(false);
                    addBookButton.setText("ADD BOOK");
                }
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter Book Title");
                addTitleField.requestFocus();
                return;
            }
            
            if (addAuthorField.getText() == null || addAuthorField.getText().trim().isEmpty()) {
                if (addBookButton != null) {
                    addBookButton.setDisable(false);
                    addBookButton.setText("ADD BOOK");
                }
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter Author");
                addAuthorField.requestFocus();
                return;
            }
            
            if (addPublisherField.getText() == null || addPublisherField.getText().trim().isEmpty()) {
                if (addBookButton != null) {
                    addBookButton.setDisable(false);
                    addBookButton.setText("ADD BOOK");
                }
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter Publisher");
                addPublisherField.requestFocus();
                return;
            }
            
            if (addCategoryField.getText() == null || addCategoryField.getText().trim().isEmpty()) {
                if (addBookButton != null) {
                    addBookButton.setDisable(false);
                    addBookButton.setText("ADD BOOK");
                }
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter Category");
                addCategoryField.requestFocus();
                return;
            }
            
            if (addQuantityField.getText() == null || addQuantityField.getText().trim().isEmpty()) {
                if (addBookButton != null) {
                    addBookButton.setDisable(false);
                    addBookButton.setText("ADD BOOK");
                }
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter Quantity");
                addQuantityField.requestFocus();
                return;
            }
            
            if (addShelfField.getText() == null || addShelfField.getText().trim().isEmpty()) {
                if (addBookButton != null) {
                    addBookButton.setDisable(false);
                    addBookButton.setText("ADD BOOK");
                }
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please enter Shelf Location");
                addShelfField.requestFocus();
                return;
            }
            
            // Parse quantity
            int quantity;
            try {
                quantity = Integer.parseInt(addQuantityField.getText().trim());
                if (quantity <= 0) {
                    if (addBookButton != null) {
                        addBookButton.setDisable(false);
                        addBookButton.setText("ADD BOOK");
                    }
                    showAlert(Alert.AlertType.WARNING, "Validation Error", "Quantity must be greater than 0");
                    addQuantityField.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                if (addBookButton != null) {
                    addBookButton.setDisable(false);
                    addBookButton.setText("ADD BOOK");
                }
                showAlert(Alert.AlertType.WARNING, "Validation Error", "Quantity must be a valid number");
                addQuantityField.requestFocus();
                return;
            }
            
            // Create book object
            Book book = new Book();
            book.setIsbn(addIsbnField.getText().trim());
            book.setTitle(addTitleField.getText().trim());
            book.setAuthor(addAuthorField.getText().trim());
            book.setPublisher(addPublisherField.getText().trim());
            book.setCategory(addCategoryField.getText().trim());
            book.setQuantity(quantity);
            book.setAvailableQuantity(quantity); // Set available quantity equal to total quantity
            book.setShelfLocation(addShelfField.getText().trim());
            
            // Add book to database
            System.out.println("Attempting to add book: " + book.getTitle());
            bookService.addBook(book);
            System.out.println("Book added successfully!");
            
            // Show success message
            showAlert(Alert.AlertType.INFORMATION, "Success", 
                "Book '" + book.getTitle() + "' has been added successfully!");
            
            // Clear fields and refresh data
            clearBookFields();
            loadData();
            updateStatistics();
            
            // Re-enable button
            if (addBookButton != null) {
                addBookButton.setDisable(false);
                addBookButton.setText("ADD BOOK");
            }
            
        } catch (NumberFormatException e) {
            if (addBookButton != null) {
                addBookButton.setDisable(false);
                addBookButton.setText("ADD BOOK");
            }
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a valid quantity (number)");
            addQuantityField.requestFocus();
        } catch (Exception e) {
            if (addBookButton != null) {
                addBookButton.setDisable(false);
                addBookButton.setText("ADD BOOK");
            }
            String errorMessage = e.getMessage();
            if (errorMessage != null && errorMessage.contains("ISBN")) {
                showAlert(Alert.AlertType.ERROR, "Duplicate ISBN", 
                    "A book with this ISBN already exists. Please use a different ISBN.");
                addIsbnField.requestFocus();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add book: " + errorMessage);
            }
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleDeleteBook() {
        Book selected = booksTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a book to delete");
            return;
        }
        
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Delete Book");
        confirm.setContentText("Are you sure you want to delete this book?");
        
        if (confirm.showAndWait().get() == ButtonType.OK) {
            try {
                bookService.deleteBook(selected.getId());
                showAlert(Alert.AlertType.INFORMATION, "Success", "Book deleted successfully!");
                loadData();
                updateStatistics();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
            }
        }
    }
    
    @FXML
    private void handleAddMember() {
        try {
            Member member = new Member();
            member.setMemberId(addMemberIdField.getText().isEmpty() ? null : addMemberIdField.getText());
            member.setName(addMemberNameField.getText());
            member.setEmail(addMemberEmailField.getText());
            member.setPhone(addMemberPhoneField.getText());
            member.setAddress(addMemberAddressField.getText());
            member.setStatus(Member.Status.ACTIVE);
            
            Member saved = memberService.addMember(member);
            showAlert(Alert.AlertType.INFORMATION, "Success", 
                "Member added successfully! Member ID: " + saved.getMemberId());
            
            clearMemberFields();
            loadData();
            updateStatistics();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
    
    @FXML
    private void handleDeleteMember() {
        Member selected = membersTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a member to delete");
            return;
        }
        
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText("Delete Member");
        confirm.setContentText("Are you sure you want to delete this member?");
        
        if (confirm.showAndWait().get() == ButtonType.OK) {
            try {
                memberService.deleteMember(selected.getId());
                showAlert(Alert.AlertType.INFORMATION, "Success", "Member deleted successfully!");
                loadData();
                updateStatistics();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
            }
        }
    }
    
    @FXML
    private void handleRefresh() {
        loadData();
        updateStatistics();
    }
    
    @FXML
    private void handleLogout() {
        CurrentUser.setUser(null);
        stageManager.showLoginScene();
    }
    
    private void clearBookFields() {
        addIsbnField.clear();
        addTitleField.clear();
        addAuthorField.clear();
        addPublisherField.clear();
        addCategoryField.clear();
        addQuantityField.clear();
        addShelfField.clear();
    }
    
    private void clearMemberFields() {
        addMemberIdField.clear();
        addMemberNameField.clear();
        addMemberEmailField.clear();
        addMemberPhoneField.clear();
        addMemberAddressField.clear();
    }
    
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

