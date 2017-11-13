package fi.kajstrom.kitcheninventory.integration.Adapters.Database;


import fi.kajstrom.kitcheninventory.Adapters.Database.IngredientTypeGateway;
import fi.kajstrom.kitcheninventory.integration.IntegrationTest;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class IngredientTypeGatewayTest extends IntegrationTest {
    private IngredientTypeGateway makeIngredientTypeGateway() {
        try {
            return new IngredientTypeGateway(getConnection());
        } catch (SQLException e) {
            Assert.fail(e.getMessage());
            return null;
        }
    }

    @Test
    public void testAll_WhenCalled_ContainsSugar() {
        IngredientTypeGateway itg = makeIngredientTypeGateway();

        List<Map<String, Object>> types = itg.all();

        Optional<Map<String, Object>> sugar =
                types.stream().filter((el) -> (Integer)el.get("id") == 1).findFirst();

        Assert.assertEquals("Sugar", sugar.get().get("name"));
    }
}
