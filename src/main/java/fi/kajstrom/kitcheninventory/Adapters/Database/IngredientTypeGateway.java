package fi.kajstrom.kitcheninventory.Adapters.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientTypeGateway {
    private Connection connection;

    public IngredientTypeGateway(Connection connection) {
        this.connection = connection;
    }

    public List<Map<String, Object>> all() {
        List<Map<String, Object>> all = new ArrayList<>();

        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM ingredient_types";

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                HashMap<String, Object> m = new HashMap<>();

                m.put("id", rs.getInt("ingredient_type_id"));
                m.put("name", rs.getString("name"));
                m.put("quantity_unit", rs.getString("quantity_unit"));

                all.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Query error!");
        }

        return all;
    }
}
