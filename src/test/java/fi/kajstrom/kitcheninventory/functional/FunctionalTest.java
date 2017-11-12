package fi.kajstrom.kitcheninventory.functional;

import fi.kajstrom.kitcheninventory.Application;
import fi.kajstrom.kitcheninventory.util.DatabaseSetup;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import spark.Spark;

import java.io.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalTest {
    @BeforeClass
    public static void beforeClass() {
        String[] args = new String[1];
        args[0] = "test";
        Application.main(args);

        DatabaseSetup dbSetup = new DatabaseSetup(Application.connection);
        dbSetup.setup();
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
