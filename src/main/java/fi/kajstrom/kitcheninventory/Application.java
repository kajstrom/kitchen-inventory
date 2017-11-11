package fi.kajstrom.kitcheninventory;

import fi.kajstrom.kitcheninventory.Adapters.REST.IngredientTypeAdapter;
import fi.kajstrom.kitcheninventory.util.Path;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        port(7777);

        get(Path.REST.INGREDIENTTYPES, IngredientTypeAdapter.fetchAllTypes);
    }
}
