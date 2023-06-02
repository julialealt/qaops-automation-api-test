package qaops.automation.api.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import qaops.automation.api.support.api.PetApi;
import qaops.automation.api.support.api.UserApi;
import qaops.automation.api.support.config.ConfigManager;
import qaops.automation.api.support.config.ServerConfig;

public class Config {

    private UserApi userApi;
    private PetApi petApi;

    public Config() {
        userApi = new UserApi();
        petApi = new PetApi();
    }

    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        ServerConfig properties = ConfigManager.getConfiguration();

        RestAssured.baseURI = String.format("%s:%d", properties.baseURI(), properties.port());
        RestAssured.basePath = properties.basePath();

        RestAssured.requestSpecification = new RequestSpecBuilder().
                addHeader("Authorization", getToken()).
                setContentType(ContentType.JSON).
                build();
    }

    private String getToken() {
        return "grant access";
    }

    @After("@deleteAllUsers")
    public void deleteAllUsers() {

        userApi.deleteAllUsers();
    }

    @After("@DeleteExtraPets")
    public void deleteExtraPets() {
        petApi.deleteExtraPets("available");
    }
}
