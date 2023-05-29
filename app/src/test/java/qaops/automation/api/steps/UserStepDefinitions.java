package qaops.automation.api.steps;

import io.cucumber.docstring.DocString;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import qaops.automation.api.support.api.UserApi;
import qaops.automation.api.support.domain.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserStepDefinitions {

    //private Map<String, String> expectedUser = new HashMap<>();
    private User user;
    private final UserApi userApi;

    public UserStepDefinitions() {
        userApi = new UserApi();
    }

    @Quando("crio um usuario")
    public void crioUmUsuario() {
        user = User.builder().build();
        userApi.createUser(user);
    }

    @Entao("o usuario eh salvo no sistema")
    public void oUsuarioESalvoNoSistema() {
        String actualUsername = UserApi.getUsername(user);

        assertThat(actualUsername, is(user.getUsername()));
    }





    // CENÁRIOS SEMELHANTES - MAIS ESPECÍFICOS
    /*
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
    */
}
