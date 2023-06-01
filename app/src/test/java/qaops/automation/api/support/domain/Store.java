package qaops.automation.api.support.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Store {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;
}
