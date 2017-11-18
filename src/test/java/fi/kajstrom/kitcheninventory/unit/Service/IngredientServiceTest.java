package fi.kajstrom.kitcheninventory.unit.Service;

import fi.kajstrom.kitcheninventory.Adapters.Database.IngredientGateway;
import fi.kajstrom.kitcheninventory.Adapters.Database.IngredientTypeGateway;
import fi.kajstrom.kitcheninventory.Service.IngredientService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class IngredientServiceTest {

    public IngredientService makeIngredientService() {
        IngredientTypeGateway ingredientTypeGateway = mock(IngredientTypeGateway.class);
        Map<String, Object> sugarType = new HashMap<>();
        sugarType.put("id", 1);
        sugarType.put("name", "Sugar");
        sugarType.put("quantity_unit", "Grams");

        List <Map<String, Object>> ingredientTypes = new ArrayList<Map<String, Object>>();
        ingredientTypes.add(sugarType);

        when(ingredientTypeGateway.all()).thenReturn(ingredientTypes);

        IngredientGateway ingredientGateway = mock(IngredientGateway.class);
        try {
            when(ingredientGateway.add(1, 20.00)).thenReturn(1);
        } catch (SQLException e) {
            fail(e.getMessage());
        }

        return  new IngredientService(ingredientGateway, ingredientTypeGateway);
    }

    @Test
    public void testAdd_WhenCalledWithValidIngredientTypeId_ReturnsInsertedId() {
        IngredientService is = makeIngredientService();

        Integer ingredientId = is.add(1, 20.00);

        assertEquals((Integer) 1, ingredientId);
    }

    @Test(expected = RuntimeException.class)
    public void testAdd_WhenCalledWithInvalidaIngredientTypeId_ThrowsInvalidArgumentException() {
        IngredientService is = makeIngredientService();

        is.add(5000, 20.00);
    }
}
