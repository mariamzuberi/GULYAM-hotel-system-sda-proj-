
package hotel.management.system;

public class CreditCard implements PaymentMethod {
    public String name() {
        return "Credit Card";
    }
    public PaymentType type() {
        return new CardType();
    }
    public float amount() {
        return 5200.0f;
    }
}