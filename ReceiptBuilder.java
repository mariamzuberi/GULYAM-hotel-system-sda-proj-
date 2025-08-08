package hotel.management.system;

public class ReceiptBuilder {
    public Receipt buildCashReceipt() {
        Receipt r = new Receipt();
        r.addMethod(new Cash());
        return r;
    }

    public Receipt buildCardReceipt() {
        Receipt r = new Receipt();
        r.addMethod(new CreditCard());
        return r;
    }

    public Receipt buildOnlineReceipt() {
        Receipt r = new Receipt();
        r.addMethod(new OnlinePayment());
        return r;
    }
}