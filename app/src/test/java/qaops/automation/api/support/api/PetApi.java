package qaops.automation.api.support.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import qaops.automation.api.support.domain.Pet;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetApi {

    private static final String FIND_PETS_BY_STATUS_ENDPOINT = "/v3/pet/findByStatus?status={status}";

    public List<Pet> getPetsByStatus(String status) {
        return given().
            pathParam("status", status).
        when().
            get(FIND_PETS_BY_STATUS_ENDPOINT).
        then().
            extract().body().jsonPath().getList("", Pet.class);
    }

    public Response getPetsResponseByStatus(String status) {
        //RequestSpecification httpRequest = RestAssured.given();
        //httpRequest.pathParam("status", status);
        //return httpRequest.get(FIND_PETS_BY_STATUS_ENDPOINT);

        return given().
            pathParam("status", status).
        when().
            get(FIND_PETS_BY_STATUS_ENDPOINT);
    }

}
