package clients;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.ConfigManager;

public class AuthClient {

    public Response login(String email, String password) {
        String baseUrl = ConfigManager.get("base.url");
        String loginPath = ConfigManager.get("login.path");
        String loginUrl = baseUrl + loginPath;

        return RestAssured.given()
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\", \"password\":\"" + password + "\"}")
                .post(loginUrl);
    }
}

