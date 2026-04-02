package base;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import utils.ConfigManager;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.get("base.url");
    }
}