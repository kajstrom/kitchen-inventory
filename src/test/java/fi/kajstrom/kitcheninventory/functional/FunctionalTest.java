package fi.kajstrom.kitcheninventory.functional;

import fi.kajstrom.kitcheninventory.Application;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import spark.Spark;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class FunctionalTest {
    @BeforeClass
    public static void beforeClass() {
        Application.main(null);
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    public CloseableHttpResponse execute(HttpUriRequest request)
        throws IOException {
        return HttpClientBuilder.create().build().execute(request);
    }

    public String responseBodyAsString(HttpResponse response)
        throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        response.getEntity().writeTo(out);

        return out.toString();
    }
}
