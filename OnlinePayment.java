package hotel.management.system;

public class OnlinePayment implements PaymentMethod {
    public String name() {
        return "Online";
    }
    public PaymentType type() {
        return new OnlineType();
    }
    public float amount() {
        return 5100.0f;
    }
}