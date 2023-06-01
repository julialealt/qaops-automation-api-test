package qaops.automation.api.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import qaops.automation.api.support.api.StoreApi;
import qaops.automation.api.support.domain.Order;
import qaops.automation.api.support.domain.Pet;
import qaops.automation.api.support.api.PetApi;
import qaops.automation.api.support.domain.builders.OrderBuilder;

import static org.hamcrest.CoreMatchers.is;

public class StoreStepDefinitions {

    PetApi petApi;
    Pet expectedPet;
    StoreApi storeApi;
    Order expectedOrder;

    public StoreStepDefinitions() {
        petApi = new PetApi();
        storeApi = new StoreApi();
    }

    @Dado("que eu possua animal {word}")
    public void queEuPossuaAnimalAvailable(String status) {
        Pet pet = Pet.builder().id(45).build();

        expectedPet = petApi.createPet(pet);
    }


    @Quando("faco o pedido desse animal")
    public void facoOPedidoDesseAnimal() {
        Order order = new OrderBuilder().withId(222).withPetId(expectedPet.getId()).build();
        System.out.println(order);
        expectedOrder = storeApi.createOrder(order);

        System.out.println("stopando");
    }

    @Entao("o pedido eh aprovado")
    public void oPedidoEhAprovado() {
        Response actualOrderResponse = storeApi.getOrder(expectedOrder.getId());
        actualOrderResponse.
            then().
                body(
                        "id", is(expectedOrder.getId()),
                        "petId", is(expectedPet.getId()),
                        "quantity", is(expectedOrder.getQuantity()),
                        "status", is("approved")
                );
    }
}
