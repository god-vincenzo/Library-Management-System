package com.library;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Launcher that properly integrates Spring Boot and JavaFX
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JavafxLauncher extends Application {
    
    private static ConfigurableApplicationContext springContext;
    private static String[] savedArgs;
    
    public static void main(String[] args) {
        savedArgs = args;
        Application.launch(JavafxLauncher.class, args);
    }
    
    @Override
    public void init() throws Exception {
        // Start Spring Boot in the init method (runs on background thread)
        springContext = new SpringApplicationBuilder(JavafxLauncher.class)
                .headless(false)
                .run(savedArgs);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Get StageManager from Spring context and show login
            com.library.ui.StageManager stageManager = springContext.getBean(com.library.ui.StageManager.class);
            stageManager.setPrimaryStage(primaryStage);
            stageManager.showLoginScene();
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
        }
    }
    
    @Override
    public void stop() throws Exception {
        if (springContext != null) {
            springContext.close();
        }
        super.stop();
    }
    
    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }
}
