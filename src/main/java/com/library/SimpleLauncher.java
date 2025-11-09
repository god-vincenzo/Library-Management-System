package com.library;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.SpringApplication;

/**
 * Simple launcher that starts Spring Boot first, then JavaFX
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SimpleLauncher {
    
    private static ConfigurableApplicationContext springContext;
    
    public static void main(String[] args) {
        // Start Spring Boot
        springContext = SpringApplication.run(SimpleLauncher.class, args);
        
        // Start JavaFX on JavaFX Application Thread
        Platform.startup(() -> {
            try {
                Application javafxApp = new JavaFXApp();
                Stage primaryStage = new Stage();
                javafxApp.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    static class JavaFXApp extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
            try {
                springContext.getBean(com.library.ui.StageManager.class).setPrimaryStage(primaryStage);
                springContext.getBean(com.library.ui.StageManager.class).showLoginScene();
            } catch (Exception e) {
                e.printStackTrace();
                Platform.exit();
            }
        }
    }
    
    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }
}

