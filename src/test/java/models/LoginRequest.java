package models;

public class LoginRequest {
    public String username;
    public String password;

    // Constructor
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters (and optionally setters)
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}