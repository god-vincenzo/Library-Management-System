package com.library.ui;

import com.library.model.User;

public class CurrentUser {
    private static User currentUser;
    
    public static void setUser(User user) {
        currentUser = user;
    }
    
    public static User getUser() {
        return currentUser;
    }
    
    public static boolean isAdmin() {
        return currentUser != null && currentUser.getRole() == User.Role.ADMIN;
    }
}

