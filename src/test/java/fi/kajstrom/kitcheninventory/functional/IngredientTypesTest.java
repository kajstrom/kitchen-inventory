package fi.kajstrom.kitcheninventory.functional;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

public class IngredientTypesTest extends FunctionalTest {
    @Test
    public void testGetIngredientTypes_WhenCalled_ShouldReturnSugar()
        throws IOException {

        HttpUriRequest request = new HttpGet("http://localhost:7777/ingredienttypes");

        HttpResponse response = execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
        assertTrue(responseBodyAsString(response).contains("Sugar"));
    }
}
