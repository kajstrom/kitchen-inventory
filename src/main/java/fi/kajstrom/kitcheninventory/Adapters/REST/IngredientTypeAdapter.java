package fi.kajstrom.kitcheninventory.Adapters.REST;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;
import spark.Route;
import static fi.kajstrom.kitcheninventory.Application.ingredientTypeService;

public class IngredientTypeAdapter {
    public static Route fetchAllTypes = (Request req, Response res) -> {
        ObjectMapper objectMapper = new ObjectMapper();

        res.type("application/json");
        return objectMapper.writeValueAsString(ingredientTypeService.fetchAllTypes());
    };
}
