package hotel.management.system;

import java.util.List;

public interface CustomerDatabaseAdapter {
    List<String> getCustomerNumbers();
    String getRoomNumberByCustomerNumber(String customerNumber);
    boolean checkOutCustomer(String customerNumber, String roomNumber);
}
