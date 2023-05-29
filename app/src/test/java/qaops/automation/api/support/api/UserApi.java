package qaops.automation.api.support.api;

import org.apache.http.HttpStatus;
import qaops.automation.api.support.domain.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class UserApi {

    private static final String CREATE_USER_ENDPOINT = "/v3/user";
    private static final String USER_ENDPOINT = "/v3/user/{name}";

    public void createUser(User user) {
        given().
            body(user).
        when().
            post(CREATE_USER_ENDPOINT).
        then().
            statusCode(HttpStatus.SC_OK);
    }

    public static String getUsername(User user) {
        return given().
            pathParam("name", user.getUsername()).
        when().
            get(USER_ENDPOINT).
        thenReturn().
            path("username");
    }
}
