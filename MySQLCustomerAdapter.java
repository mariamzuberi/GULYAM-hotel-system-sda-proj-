package hotel.management.system;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCustomerAdapter implements CustomerDatabaseAdapter {

    conn c = conn.getInstance();
    Statement s = c.getStatement();

    @Override
    public List<String> getCustomerNumbers() {
        List<String> numbers = new ArrayList<>();
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM customer");
            while (rs.next()) {
                numbers.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numbers;
    }

    @Override
    public String getRoomNumberByCustomerNumber(String customerNumber) {
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM customer WHERE number = '" + customerNumber + "'");
            if (rs.next()) {
                return rs.getString("roomnumber");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkOutCustomer(String customerNumber, String roomNumber) {
        try {
            s.executeUpdate("DELETE FROM customer WHERE number = '" + customerNumber + "'");
            s.executeUpdate("UPDATE room SET availability = 'Available' WHERE roomnumber = '" + roomNumber + "'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
