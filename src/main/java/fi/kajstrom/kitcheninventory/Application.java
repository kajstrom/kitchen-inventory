package fi.kajstrom.kitcheninventory;

import fi.kajstrom.kitcheninventory.Adapters.REST.IngredientTypeAdapter;
import fi.kajstrom.kitcheninventory.Service.IngredientTypeService;
import fi.kajstrom.kitcheninventory.util.Path;

import static spark.Spark.*;

public class Application {

    public static IngredientTypeService ingredientTypeService;

    public static void main(String[] args) {
        ingredientTypeService = new IngredientTypeService();

        port(7777);

        get(Path.REST.INGREDIENTTYPES, IngredientTypeAdapter.fetchAllTypes);
    }
}
