package hotel.management.system;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    public List<PaymentMethod> methods = new ArrayList<>();

    public void addMethod(PaymentMethod method) {
        methods.add(method);
    }

    public float getTotalAmount() {
        float total = 0.0f;
        for (PaymentMethod m : methods) {
            total += m.amount();
        }
        return total;
    }

    public void showReceipt() {
        for (PaymentMethod m : methods) {
            System.out.println("Payment: " + m.name());
            System.out.println("Type: " + m.type().type());
            System.out.println("Amount: Rs." + m.amount());
            System.out.println("------------");
        }
        System.out.println("Total Paid: Rs." + getTotalAmount());
    }
}