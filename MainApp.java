package hotel.management.system;
public class MainApp {
    public static void main(String[] args) {
        CustomerDataModel model = new CustomerDataModel();
        CustomerInfo customerInfo = new CustomerInfo(model);
        customerInfo.setVisible(true);

        // Simulate data change (e.g., after insert/update/delete in DB)
        // model.customerDataChanged();
    }
}