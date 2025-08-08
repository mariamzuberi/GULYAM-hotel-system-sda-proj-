package hotel.management.system;

import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface TableDataAdapter {
    TableModel convert(ResultSet rs) throws SQLException;
}
