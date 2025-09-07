package com.foodApp.Model;

public class User {
    private int uid;
    private String username;
    private String email;
    private String mobile;
    private String password;

    // Default constructor
    public User() {
        super();
    }

    // Constructor with all parameters
    public User(int uid, String username, String email, String mobile, String password) {
        super();
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    // Constructor with selected parameters
    public User(String username, String email, String mobile, String password) {
        super();
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    // Constructor with email only
    public User(String email) {
        super();
        this.email = email;
    }

    // Getters
    public int getUid() {
        return uid;
    }
    
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method
    @Override
    public String toString() {
        return uid + " " + username + " " + email + " " + mobile + " " + password;
    }
}
