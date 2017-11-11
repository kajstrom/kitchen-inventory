package fi.kajstrom.kitcheninventory.Adapters.REST;

import com.fasterxml.jackson.databind.ObjectMapper;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientTypeAdapter {
    public static Route fetchAllTypes = (Request req, Response res) -> {
        ArrayList<HashMap<String, Object>> types = new ArrayList<>();
        HashMap<String, Object> sugar = new HashMap<>();
        sugar.put("id", 1);
        sugar.put("name", "Sugar");
        sugar.put("quantity_unit", "Gram");

        types.add(sugar);

        ObjectMapper objectMapper = new ObjectMapper();

        res.type("application/json");
        return objectMapper.writeValueAsString(types);
    };
}
