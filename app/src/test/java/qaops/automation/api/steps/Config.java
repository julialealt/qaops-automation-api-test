package qaops.automation.api.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class Config {

    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.baseURI = "http://localhost:12345";
        RestAssured.basePath = "/api";

        RestAssured.requestSpecification = new RequestSpecBuilder().
                addHeader("Authorization", getToken()).
                setContentType(ContentType.JSON).
                build();

        RestAssured.responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                build();
    }

    private String getToken() {
        return "grant access";
    }

    @Before(order = 1)
    public void doSomething() {
        System.out.println("hook before");
    }

    @Before(value = "@primeira", order = 3)
    public void doFirst() {
        System.out.println("before primeiro");
    }

    @Before(value = "@segunda", order = 2)
    public void doSecond() {
        System.out.println("before segundo");
    }

    @Before(value = "@terceira", order = 1)
    public void doThird() {
        System.out.println("before terceiro");
    }

    @After(value = "@primeira", order = 3)
    public void doLast() {
        System.out.println("after primeiro");
    }

    @After(value = "@segunda", order = 3)
    public void doLast2() {
        System.out.println("after segundo");
    }

    @After(value = "@terceira", order = 3)
    public void doLast3() {
        System.out.println("after terceiro");
    }
}
