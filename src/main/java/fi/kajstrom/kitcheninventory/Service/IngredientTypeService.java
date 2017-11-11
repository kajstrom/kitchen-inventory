package fi.kajstrom.kitcheninventory.Service;

import java.util.*;

public class IngredientTypeService {
    public List<Map<String, Object>> fetchAllTypes() {
        List<Map<String, Object>> types = new ArrayList<>();

        Map<String, Object> sugar = new HashMap<>();
        sugar.put("id", 1);
        sugar.put("name", "Sugar");
        sugar.put("quantity_unit", "Gram");

        types.add(sugar);

        return types;
    }
}
