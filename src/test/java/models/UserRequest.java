package models;

public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String groupId;

    // Constructor
    public UserRequest(String firstName, String lastName, String email, String password, String confirmPassword, String groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.groupId = groupId;
    }

    // Getters (optional: add setters if needed)
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getConfirmPassword() { return confirmPassword; }
    public String getGroupId() { return groupId; }
}