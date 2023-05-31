package qaops.automation.api.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import qaops.automation.api.support.api.PetApi;
import qaops.automation.api.support.domain.Pet;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetStepDefinitions {

        private PetApi petApi;
        private List<Pet> actualPets;

        public PetStepDefinitions() {
                petApi = new PetApi();
        }

        @Dado("que eu possua animais available")
        public void queEuPossuaAnimaisAvailable() {
                /*
                Pet pet = Pet.builder().build();
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(pet);
                System.out.println(json);
                System.out.println("break point");
                 */
        }

        @Quando("eu pesquiso por todos os animais {word}")
        public void queEuPesquisoPorTodosOsAnimaisAvailable(String status) {
                List<Pet> actualPets = petApi.getPetsByStatus(status);
        }

        @Entao("eu recebo a lista de animais available")
        public void euReceboAListaDeAnimaisAvailable() {
                assertThat(actualPets, is(not(empty())));
        }

        @E("eu recebo uma outra lista de animais {word}")
        public void euReceboUmaOutraListaDeAnimaisAvailable(String status) {
                Response actualAvailablePetsResponse = petApi.getPetsResponseByStatus(status);

                actualPets = actualAvailablePetsResponse.body().jsonPath().getList("", Pet.class);

                actualAvailablePetsResponse.
                        then().
                                statusCode(HttpStatus.SC_OK).
                                body(
                                        "size()", is(actualPets.size()),
                                        "findAll { it.status == '"+status+"' }.size()", is(actualPets.size())
                                );
        }
}
