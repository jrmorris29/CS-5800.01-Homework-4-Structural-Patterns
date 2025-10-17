package bridge;

public class CashOnDeliveryPayment extends PaymentNotification {

    public CashOnDeliveryPayment(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public void notifyCustomer() {
        channel.send("Your cash on delivery order has been placed. Please pay upon delivery.");
    }
}
