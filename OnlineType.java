package hotel.management.system;

public class OnlineType implements PaymentType {
    public String type() {
        return "Online Bank Transfer";
    }
}