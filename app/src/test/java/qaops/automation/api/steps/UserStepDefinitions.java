package qaops.automation.api.steps;

import io.cucumber.docstring.DocString;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import qaops.automation.api.support.domain.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class UserStepDefinitions {

    private static final String CREATE_USER_ENDPOINT = "/v3/user";
    private static final String USER_ENDPOINT = "/v3/user/{name}";

    private Map<String, String> expectedUser = new HashMap<>();
    private User user;

    @Quando("eu faco um POST para {word} com os seguintes valores:")
    public void facoUmPOSTParaEPComOsSeguintesValores(String endpoint, Map<String, String> user) {
        expectedUser = user;

        given().
                body(user).
        when().
                post(endpoint).
        then().
                statusCode(HttpStatus.SC_OK);
    }

    @Entao("quando faco um GET para {word}, o usuario criado eh retornado")
    public void quandoFacoUmGETParaEPOUsuarioCriadoERetornado(String endpoint) {
        when().
                get(endpoint).
        then().
                statusCode(HttpStatus.SC_OK).
                body("username", is(expectedUser.get("username")));
    }

    @Quando("faco um POST para {word} com a seguinte docstring:")
    public void facoUmPOSTParaVUserComASeguinteDocstring(String endpoint, DocString docstring) {
        expectedUser.put("username", "theUser");

        given().
                body(docstring.getContent()).
        when().
                post(endpoint).
        then().
                statusCode(HttpStatus.SC_OK);
    }


    @Quando("crio um usuario")
    public void crioUmUsuario() {
        user = User.builder().build();

        given().
                body(user).
        when().
                post(CREATE_USER_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK);

    }

    @Entao("o usuario eh salvo no sistema")
    public void oUsuarioESalvoNoSistema() {
        given().
            pathParam("name", user.getUsername()).
        when().
            get(USER_ENDPOINT).
        then().
            statusCode(HttpStatus.SC_OK).
            body("username", is(user.getUsername()));
    }
}
