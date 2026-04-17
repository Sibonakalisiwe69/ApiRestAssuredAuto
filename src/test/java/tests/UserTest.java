package tests;
import base.BaseTest;
import clients.AuthClient;
import clients.UserClient;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import models.UserRequest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigManager;

import java.util.UUID;

public class UserTest extends BaseTest {

    AuthClient authClient = new AuthClient(); //creating an instance of a class
    UserClient userClient = new UserClient();

    String adminToken; // variable declaration adminToken of type String
    String userId;

    //declaring and initializing a string variable
    /*String email = "luh" + System.currentTimeMillis() + "@example.com"; // Generate unique email per test run*/
    String email = "user_" + UUID.randomUUID() + "@example.com";// Generate unique email using UUID for better uniqueness per test run
    String password = ConfigManager.get("default.user.password"); // Read the default user password from the configuration file

    @Test(priority = 1) // Set priority to ensure this runs first
    @Description("Create new user (register)")//Description for Allure report
    public void registerUser() {
        //creating an instance of a class
        UserRequest req = new UserRequest(
                "Luh",
                "Mhayise",
                email,
                password,
                password,
                "1deae17a-c67a-4bb0-bdeb-df0fc9e2e526"
        );

        Response res = userClient.registerUser(adminToken, req);//Create user using the UserClient and the generated email and password

        // Debug print
        System.out.println("Create User Response: " + res.asString());//

        // Correct JSON path
        userId = res.jsonPath().getString("data.id");
        System.out.println("Extracted userId: " + userId);

        Assert.assertNotNull(userId, "User ID should not be null");
    }

    @Test(priority = 2)
    @Description("Login as Admin")
    public void adminLogin() {
        Response res = authClient.login(
                ConfigManager.get("admin.email"),
                ConfigManager.get("admin.password")
        );
        System.out.println(res.asString());// Print the full response for debugging
        adminToken = res.jsonPath().getString("data.token"); //  Extract the token
        Assert.assertNotNull(adminToken, "Admin token should not be null!");//Assert token is not null
    }

    @Test(priority = 3)
    @Description("Approve new user")
    public void approveUserTest() {
        Response res = userClient.approveUser(adminToken, userId);

        System.out.println("Response: " + res.asString());
        Assert.assertEquals(res.statusCode(), 200, "User approval failed!");
    }


    @Test(priority = 4)
    @Description("Promote new user to Admin")
    public void promoteToAdmin() {
        Response res = userClient.promoteToAdmin(adminToken, userId);//Promote the user to admin using the extracted userId and adminToken
        System.out.println("Response: " + res.asString());
        Assert.assertEquals(200, res.statusCode());

    }

    @Test(priority = 5)
    @Description("Login with new user")
    public void loginWithNewUser() {
        Response res = authClient.login(email, password);
        String token = res.jsonPath().getString("data.token");//Extract token from response
        System.out.println("Response: " + res.asString());
        Assert.assertNotNull(token);
    }

    @Test(priority = 6)
    @Description("Verify new user has Admin role")
    public void verifyAdminRole() {
        Response res = userClient.getUser(adminToken, userId); //Get user details using adminToken and userId
        System.out.println("Response: " + res.asString());
        //Assert.assertEquals(res.jsonPath().getString("data.user.Role"), "admin");//Assert that the user's role is admin
        String role = res.jsonPath().getString("data.Role");//Extract the role from the response using the correct JSON path
        Assert.assertEquals("admin", role);//Assert that the user's role is admin
    }

    @Test(priority = 7)
    @Description("Delete newly created user")
    public void deleteUser() {
        Response res = userClient.deleteUser(adminToken, userId);
        System.out.println("Response: " + res.asString());
        Assert.assertEquals(res.statusCode(), 200);
    }
}