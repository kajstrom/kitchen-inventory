package fi.kajstrom.kitcheninventory.functional;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;

public class IngredientTypesTest extends FunctionalTest {
    @Test
    public void testGetIngredientTypes_WhenCalled_ShouldReturnSugar()
        throws IOException {

        HttpUriRequest request = new HttpGet("http://localhost:7777/ingredienttypes");

        HttpResponse response = execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());

        ObjectMapper mapper = new ObjectMapper();
        HashMap[] array = mapper.readValue(responseBodyAsString(response), HashMap[].class);
        List<Map<String, Object>> lm = Arrays.asList(array);
        Optional<Map<String, Object>> sugar =
                lm.stream().filter((el) -> (Integer)el.get("id") == 1).findFirst();

        assertEquals("Sugar", sugar.get().get("name"));
    }
}
