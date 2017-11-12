package fi.kajstrom.kitcheninventory.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseSetup {
    private Connection connection;

    public DatabaseSetup(Connection connection) {
        this.connection = connection;
    }

    public void setup() {
        try {
            Statement stmt = connection.createStatement();
            executeQueriesInFile(stmt, "/database/schema.sql");

            stmt = connection.createStatement();
            executeQueriesInFile(stmt, "/database/data.sql");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void executeQueriesInFile(Statement stmt, String file)
            throws SQLException{
        InputStream is = getClass().getResourceAsStream(file);

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
