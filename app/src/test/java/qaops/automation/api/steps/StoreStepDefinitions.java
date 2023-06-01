package qaops.automation.api.steps;

import io.cucumber.java.pt.Quando;
import qaops.automation.api.support.domain.Store;
import qaops.automation.api.support.domain.builders.StoreBuilder;

public class StoreStepDefinitions {

    @Quando("something")
    public void something() {
        Store store1 = new StoreBuilder().build();

        Store store2 = new StoreBuilder().
                withId(1).
                withPetId(104).
                withQuantity(4).
                withShipDate("01/06/2023").
                withStatus("pending").
                withComplete(true).
                build();

        Store store3 = new StoreBuilder().
                withQuantity(40).
                build();

        Store store4 = new StoreBuilder().build();

        System.out.println("stop here");

    }
}
