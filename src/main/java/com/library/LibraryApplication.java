package com.library;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Main application class for Library Management System
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class LibraryApplication extends Application {
    
    private static ConfigurableApplicationContext springContext;
    private static String[] savedArgs;

    public static void main(String[] args) {
        savedArgs = args;
        Application.launch(LibraryApplication.class, args);
    }

    @Override
    public void init() throws Exception {
        springContext = new SpringApplicationBuilder(LibraryApplication.class)
                .headless(false)
                .run(savedArgs);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {
        springContext.getBean(com.library.ui.StageManager.class).setPrimaryStage(primaryStage);
        springContext.getBean(com.library.ui.StageManager.class).showLoginScene();
    }

    @Override
    public void stop() throws Exception {
        springContext.close();
        super.stop();
    }

    public static ConfigurableApplicationContext getSpringContext() {
        return springContext;
    }
}

