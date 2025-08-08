package hotel.management.system;

import java.sql.*;

public class conn {
    private static conn instance;           // Single instance of the class
    private Connection c;                   // JDBC connection object
    private Statement s;                    // Statement object

    // Private constructor to restrict instantiation from outside
    private conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql:///hotelmanagementsystem", "root", "maryamz023");
            s = c.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Public method to provide access to the single instance
    public static conn getInstance() {
        if (instance == null) {
            instance = new conn();
        }
        return instance;
    }

    // Public methods to access connection and statement
    public Connection getConnection() {
        return c;
    }

    public Statement getStatement() {
        return s;
    }
}
