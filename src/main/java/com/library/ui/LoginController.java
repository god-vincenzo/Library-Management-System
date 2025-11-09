package com.library.ui;

import com.library.model.User;
import com.library.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private StageManager stageManager;
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Auto-fill for quick testing
        usernameField.setText("admin");
        passwordField.setText("admin123");
    }
    
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter username and password");
            return;
        }
        
        try {
            User user = userService.authenticate(username, password)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
            
            // Store current user (you can use a session manager for this)
            CurrentUser.setUser(user);
            
            stageManager.showDashboardScene();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password");
        }
    }
    
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

