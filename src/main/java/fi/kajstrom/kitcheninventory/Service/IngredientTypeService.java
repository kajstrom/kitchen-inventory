package fi.kajstrom.kitcheninventory.Service;


import fi.kajstrom.kitcheninventory.Adapters.Database.IngredientTypeGateway;

import java.util.*;

public class IngredientTypeService {
    private IngredientTypeGateway ingredientTypeGateway;

    public IngredientTypeService(IngredientTypeGateway ingredientTypeGateway) {
        this.ingredientTypeGateway = ingredientTypeGateway;
    }

    public List<Map<String, Object>> fetchAllTypes() {
        return ingredientTypeGateway.all();
    }
}
