package bridge;

public class BitcoinPayment extends PaymentNotification {

    public BitcoinPayment(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public void notifyCustomer() {
        channel.send("Your Bitcoin payment was received successfully.");
    }
}
