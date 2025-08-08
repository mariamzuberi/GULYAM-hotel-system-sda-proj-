package hotel.management.system;

import java.sql.*;

// Bridge Pattern: Concrete implementation for loading department data from the database
public class SQLDepartmentDataLoader implements DepartmentDataLoader {

    @Override
    public ResultSet loadData() throws SQLException {
        conn c = conn.getInstance();
        Statement s = c.getStatement(); 
        String sql = "SELECT * FROM Department";
        return s.executeQuery(sql); //singleton 
    }
}
