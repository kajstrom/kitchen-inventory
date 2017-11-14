package fi.kajstrom.kitcheninventory.Adapters.Database;

import java.sql.*;

public class IngredientGateway {
    private Connection connection;

    public IngredientGateway(Connection connection) {
        this.connection = connection;
    }

    public Integer add(Integer ingredientTypeID, Double amount)
        throws SQLException{
        String sql = "INSERT INTO ingredients(ingredient_type_id, quantity) VALUES(?,?);";

        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1,ingredientTypeID);
        stmt.setDouble(2, amount);

        stmt.execute();

        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }
}
