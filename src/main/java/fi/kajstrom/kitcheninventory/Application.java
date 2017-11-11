package fi.kajstrom.kitcheninventory;

import fi.kajstrom.kitcheninventory.Adapters.REST.IngredientTypeAdapter;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {
        port(7777);

        get("/hello", (req, res) -> "Hello World");
        get("/ingredienttypes", IngredientTypeAdapter.fetchAllTypes);
    }
}
