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

        try {
            Statement stmt = Application.connection.createStatement();
            executeQueriesInFile(stmt, "/database/schema.sql");

            stmt = Application.connection.createStatement();
            executeQueriesInFile(stmt, "/database/data.sql");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public static void executeQueriesInFile(Statement stmt, String file)
        throws SQLException{
        InputStream is = FunctionalTest.class.getResourceAsStream(file);

        String result = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining("\n"));

        String[] queries = result.split(";");
        List<String> queryList = Arrays.asList(queries);

        for (String query : queryList) {
            stmt.addBatch(query);
        }

        stmt.executeBatch();
    }
}
