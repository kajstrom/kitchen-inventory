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
            Integer port = 7777;
            if (isTestEnv(args)) {
                env = "test";
                port = 8888;
            }

            connection = newDbConnection(env);
            IngredientTypeGateway ingredientTypeGateway = new IngredientTypeGateway(connection);
            ingredientTypeService = new IngredientTypeService(ingredientTypeGateway);

            port(port);

            get(Path.REST.INGREDIENTTYPES, IngredientTypeAdapter.fetchAllTypes);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public static Connection newDbConnection(String env)
        throws SQLException{
        String url;

        if (env.equals("prod")) {
            url = "jdbc:mariadb://localhost:3306/kitcheninventory?user=root&password=r00t";
        } else {
            url = "jdbc:mariadb://localhost:3306/kitcheninventory_test?user=travis&password=";
        }

        return DriverManager.getConnection(url);
    }

    private static Boolean isTestEnv(String[] args) {
        return args.length > 0 && args[0].equals("test");
    }
}
