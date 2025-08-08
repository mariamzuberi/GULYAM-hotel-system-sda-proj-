package hotel.management.system;

import java.sql.*;
import java.awt.Choice;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class DisplayDriversCommand implements Command {
    private JTable table;
    private Choice carBrand;

    public DisplayDriversCommand(JTable table, Choice carBrand) {
        this.table = table;
        this.carBrand = carBrand;
    }

    @Override
    public void execute() {
        String SQL = "SELECT * FROM driver WHERE brand = '" + carBrand.getSelectedItem() + "'";
        try {
            conn c = conn.getInstance();               // ✅ Use singleton connection
            Statement s = c.getStatement();            // ✅ Access Statement properly
            ResultSet rs = s.executeQuery(SQL);        // ✅ Execute the query
            table.setModel(DbUtils.resultSetToTableModel(rs)); // ✅ Fill table
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
