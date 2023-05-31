package qaops.automation.api.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import qaops.automation.api.support.api.UserApi;

public class Config {

    private final UserApi userApi;

    public Config() {
        userApi = new UserApi();
    }

    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.baseURI = "http://localhost:12345";
        RestAssured.basePath = "/api";

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
}
