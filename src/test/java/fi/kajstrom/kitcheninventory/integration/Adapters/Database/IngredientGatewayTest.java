package fi.kajstrom.kitcheninventory.integration.Adapters.Database;

import fi.kajstrom.kitcheninventory.Adapters.Database.IngredientGateway;
import fi.kajstrom.kitcheninventory.integration.IntegrationTest;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class IngredientGatewayTest extends IntegrationTest {
    public IngredientGateway makeIngredientGateway() {
        try {
            return new IngredientGateway(getConnection());
        } catch (SQLException e) {
            fail(e.getMessage());
            return null;
        }
    }

    @Test
    public void testAdd_WhenCalledWithExistingTypeId_ReturnsIngredientId()
        throws SQLException{
        IngredientGateway ig = makeIngredientGateway();

        Integer ingredientId = ig.add(1, 500.0);
        assertTrue(ingredientId > 0);
    }

    @Test(expected = SQLException.class)
    public void testAdd_WhenCalledWithInvalidTypeId_ThrowsRuntimeException()
        throws SQLException{

        IngredientGateway ig = makeIngredientGateway();

        ig.add(1500, 500.0);
    }
}
