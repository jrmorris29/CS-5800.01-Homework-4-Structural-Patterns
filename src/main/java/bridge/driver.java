package bridge;

public class driver {
    public static void main(String[] args) {

        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel push = new PushChannel();

        PaymentNotification onlineEmail = new OnlinePaymentNotification(email);
        PaymentNotification onlineSms = new OnlinePaymentNotification(sms);
        PaymentNotification cashEmail = new CashOnDeliveryPayment(email);
        PaymentNotification cashSms = new CashOnDeliveryPayment(sms);
        PaymentNotification bitcoinEmail = new BitcoinPayment(email);
        PaymentNotification bitcoinSms = new BitcoinPayment(sms);
        PaymentNotification bitcoinPush = new BitcoinPayment(push);

        System.out.println("=== Payment Notification Scenarios ===");
        onlineEmail.notifyCustomer();
        onlineSms.notifyCustomer();
        cashEmail.notifyCustomer();
        cashSms.notifyCustomer();
        bitcoinEmail.notifyCustomer();
        bitcoinSms.notifyCustomer();
        bitcoinPush.notifyCustomer();
    }
}
