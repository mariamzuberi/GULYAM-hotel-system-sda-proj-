package hotel.management.system;

public class CashType implements PaymentType {
    public String type() {
        return "Cash Payment";
    }
}