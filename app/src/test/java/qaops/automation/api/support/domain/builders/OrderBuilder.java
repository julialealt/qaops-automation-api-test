package qaops.automation.api.support.domain.builders;

import qaops.automation.api.support.domain.Order;

public class OrderBuilder {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public OrderBuilder() {
        reset();
    }

    public OrderBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public OrderBuilder withPetId(int petId) {
        this.petId = petId;
        return this;
    }

    public OrderBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderBuilder withShipDate(String shipDate) {
        this.shipDate = shipDate;
        return this;
    }

    public OrderBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderBuilder withComplete(boolean complete) {
        this.complete = complete;
        return this;
    }

    public Order build() {
        return new Order(
            id,
            petId,
            quantity,
            shipDate,
            status,
            complete
        );
    }

    public void reset() {
        id = 1;
        petId = 101;
        quantity = 1;
        shipDate = "2021-02-21";
        status = "approved";
        complete = true;
    }
}
