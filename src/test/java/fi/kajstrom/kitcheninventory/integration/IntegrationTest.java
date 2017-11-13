package fi.kajstrom.kitcheninventory.integration;

import fi.kajstrom.kitcheninventory.Application;
import fi.kajstrom.kitcheninventory.util.DatabaseSetup;
import org.junit.Assert;
import org.junit.Before;

import java.sql.Connection;
import java.sql.SQLException;

public class IntegrationTest {
    static Connection connection;

    protected Connection getConnection()
        throws SQLException{
        if (connection == null) {
            connection = Application.newDbConnection("test");
        }

        return connection;
    }

    @Before
    public void before() {
        try {
            DatabaseSetup dbSetup = new DatabaseSetup(getConnection());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }
}
