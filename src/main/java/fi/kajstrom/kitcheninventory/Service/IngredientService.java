package fi.kajstrom.kitcheninventory.Service;

import fi.kajstrom.kitcheninventory.Adapters.Database.IngredientGateway;
import fi.kajstrom.kitcheninventory.Adapters.Database.IngredientTypeGateway;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class IngredientService {
    private IngredientGateway ingredientGateway;
    private IngredientTypeGateway ingredientTypeGateway;

    public IngredientService(IngredientGateway ingredientGateway, IngredientTypeGateway ingredientTypeGateway) {
        this.ingredientGateway = ingredientGateway;
        this.ingredientTypeGateway = ingredientTypeGateway;
    }

    public Integer add(Integer ingredientTypeID, Double amount) {
        try {
            List<Map<String, Object>> ingredientTypes = ingredientTypeGateway.all();

            Optional<Map<String, Object>> type = ingredientTypes
                    .stream()
                    .filter((el) -> (Integer)el.get("id") == ingredientTypeID)
                    .findFirst();

            if (!type.isPresent()) {
                throw new RuntimeException("Invalid ingredient type: " + ingredientTypeID);
            }

            return ingredientGateway.add(ingredientTypeID, amount);
        } catch (SQLException e) {
            //TODO: Handling DB transactions?
            System.out.println(e.getMessage());
            return null;
        }
    }
}
