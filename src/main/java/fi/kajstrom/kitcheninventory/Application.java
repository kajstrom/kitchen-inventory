package fi.kajstrom.kitcheninventory;

import fi.kajstrom.kitcheninventory.Adapters.Database.IngredientTypeGateway;
import fi.kajstrom.kitcheninventory.Adapters.REST.IngredientTypeAdapter;
import fi.kajstrom.kitcheninventory.Service.IngredientTypeService;
import fi.kajstrom.kitcheninventory.util.Path;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.*;

public class Application {

    public static IngredientTypeService ingredientTypeService;
    public static Connection connection;

    public static void main(String[] args) {
        try {
            String env = "prod";
            if (args.length > 0 && args[0].equals("test")) {
                env = "test";
            }

             connection = DriverManager.getConnection(connectionString(env));

            IngredientTypeGateway ingredientTypeGateway = new IngredientTypeGateway(connection);

            ingredientTypeService = new IngredientTypeService(ingredientTypeGateway);

            port(7777);

            get(Path.REST.INGREDIENTTYPES, IngredientTypeAdapter.fetchAllTypes);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static String connectionString(String env) {
        if (env.equals("prod")) {
            return "jdbc:mariadb://localhost:3306/kitcheninventory?user=root&password=r00t";
        } else {
            return "jdbc:mariadb://localhost:3306/kitcheninventory_test?user=travis&password=";
        }

    }
}
