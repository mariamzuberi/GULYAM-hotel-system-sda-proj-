package hotel.management.system;

public class CardType implements PaymentType {
    public String type() {
        return "Credit/Debit Card";
    }
}
