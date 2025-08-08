package hotel.management.system;

public class Cash implements PaymentMethod {
    public String name() {
        return "Cash";
    }
    public PaymentType type() {
        return new CashType();
    }
    public float amount() {
        return 5000.0f;
    }
}
