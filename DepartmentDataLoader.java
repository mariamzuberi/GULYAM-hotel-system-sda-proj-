package hotel.management.system;

import java.sql.ResultSet;
import java.sql.SQLException;

// Bridge Pattern: Interface for loading department data
public interface DepartmentDataLoader {
    ResultSet loadData() throws SQLException;
}
