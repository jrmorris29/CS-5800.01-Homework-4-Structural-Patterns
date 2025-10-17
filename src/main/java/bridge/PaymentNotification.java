package bridge;

public abstract class PaymentNotification {
    protected NotificationChannel channel;

    public PaymentNotification(NotificationChannel channel) {
        this.channel = channel;
    }

    public abstract void notifyCustomer();
}
