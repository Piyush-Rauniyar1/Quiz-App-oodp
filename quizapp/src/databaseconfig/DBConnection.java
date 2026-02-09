package databaseconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to manage the connection to the MySQL database.
 * Uses the Singleton pattern concept via static method.
 * @author Piyush
 * @version 1.0
 */
public class DBConnection {

    /** Database URL. */
    private static final String URL = "jdbc:mysql://localhost:3306/CompetitionDB";

    /** Database Username. */
    private static final String USER = "root";

    /** Database Password. */
    private static final String PASSWORD = "225533";

    /**
     * Establishes and returns a connection to the MySQL database.
     * @return A valid Connection object.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}