package bridge;

public class OnlinePaymentNotification extends PaymentNotification {

    public OnlinePaymentNotification(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public void notifyCustomer() {
        channel.send("Your online payment was successful.");
    }
}
