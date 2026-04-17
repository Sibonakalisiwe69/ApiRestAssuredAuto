package clients;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import utils.ConfigManager;

public class UserClient {

    public Response registerUser(String token, Object body) {
        String url = ConfigManager.get("base.url") + ConfigManager.get("create.user.path");
        return given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .post(url);
    }

    public Response approveUser(String token, String userId) {
        String path = ConfigManager.get("approve.user.path").replace("{id}", userId);// Replace {id} with actual userId in the path
        String url = ConfigManager.get("base.url") + path;
        return given()
                .header("Authorization", "Bearer " + token)
                .put(url);
    }

    public Response promoteToAdmin(String token, String userId) {
        String path = ConfigManager.get("make.admin.path").replace("{id}", userId);
        String url = ConfigManager.get("base.url") + path;
        String body = "{\"role\":\"admin\"}";
        return given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .put(url);
    }


    public Response getUser(String token, String userId) {
        String path = ConfigManager.get("get.user.path").replace("{id}", userId);
        String url = ConfigManager.get("base.url") + path;
        return given()
                .header("Authorization", "Bearer " + token)
                .get(url);
    }

    public Response deleteUser(String token, String userId) {
        String path = ConfigManager.get("delete.user.path").replace("{id}", userId);
        String url = ConfigManager.get("base.url") + path;
        return given()
                .header("Authorization", "Bearer " + token)
                .delete(url);
    }
}