import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;

import java.io.IOException;

public class WalkingSkeletonTest {
    @BeforeClass
    public static void beforeClass() {
        Application.main(null);
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void testHelloRoute_WithGetRequest_RespondsWithHttp200()
        throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:7777/hello");

        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        assertEquals(200, response.getStatusLine().getStatusCode());
    }
}
