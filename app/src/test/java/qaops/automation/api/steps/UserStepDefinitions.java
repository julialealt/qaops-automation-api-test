package qaops.automation.api.steps;

import io.cucumber.docstring.DocString;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class UserStepDefinitions {

    private Map<String, String> expectedUser = new HashMap<>();

    @Quando("eu faco um POST para {word} com os seguintes valores:")
    public void facoUmPOSTParaVUserComOsSeguintesValores(String endpoint, Map<String, String> user) {
        expectedUser = user;

        given().
                contentType(ContentType.JSON).
        when().
                post("http://localhost:12345/api" + endpoint).
        then().
                contentType(ContentType.JSON).
                statusCode(HttpStatus.SC_OK);
    }

    @Entao("quando faco um GET para {word}, o usuário criado é retornado")
    public void quandoFacoUmGETParaVUserRafaelOUsuarioCriadoERetornado(String endpoint) {
        when().
                post("http://localhost:12345/api" + endpoint).
        then().
                contentType(ContentType.JSON).
                statusCode(HttpStatus.SC_OK).
                body("username", is(expectedUser.get("username")));
    }

    @Quando("faco um POST para {word} com a seguinte docstring:")
    public void facoUmPOSTParaVUserComASeguinteDocstring(String endpoint, DocString docstring) {
        expectedUser.put("username", "theUser");

        given().
                contentType(ContentType.JSON).
                body(docstring.getContent()).
        when().
                post("http://localhost:12345/api" + endpoint).
        then().
                contentType(ContentType.JSON).
                statusCode(HttpStatus.SC_OK);
    }
}
