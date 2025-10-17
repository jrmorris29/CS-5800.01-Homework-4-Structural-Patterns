package bridge;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BridgePatternTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    public void setup() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void emailChannel_send_printsPrefix() {
        new EmailChannel().send("Test message");
        String output = outputStream.toString().trim();
        assertTrue(output.startsWith("[Email] "));
        assertTrue(output.contains("Test message"));
    }

    @Test
    void smsChannel_send_printsPrefix() {
        new SmsChannel().send("SMS test");
        String output = outputStream.toString().trim();
        assertTrue(output.startsWith("[SMS] "));
        assertTrue(output.contains("SMS test"));
    }

    @Test
    void pushChannel_send_printsPrefix() {
        new PushChannel().send("Push test");
        String output = outputStream.toString().trim();
        assertTrue(output.startsWith("[Push Notification] "));
        assertTrue(output.contains("Push test"));
    }

    @Test
    void onlinePaymentNotification_sendsExpectedMessage() {
        NotificationChannel channel = new EmailChannel();
        PaymentNotification payment = new OnlinePaymentNotification(channel);
        payment.notifyCustomer();
        assertTrue(outputStream.toString().contains("online payment was successful"));
    }

    @Test
    void cashOnDeliveryNotification_sendsExpectedMessage() {
        NotificationChannel channel = new SmsChannel();
        PaymentNotification payment = new CashOnDeliveryPayment(channel);
        payment.notifyCustomer();
        assertTrue(outputStream.toString().contains("cash on delivery order"));
    }

    @Test
    void bitcoinPaymentNotification_sendsExpectedMessage() {
        NotificationChannel channel = new PushChannel();
        PaymentNotification payment = new BitcoinPayment(channel);
        payment.notifyCustomer();
        assertTrue(outputStream.toString().contains("Bitcoin payment was received successfully"));
    }

    @Test
    void samePaymentWorksWithDifferentChannels() {
        PaymentNotification paymentEmail = new OnlinePaymentNotification(new EmailChannel());
        paymentEmail.notifyCustomer();
        assertTrue(outputStream.toString().contains("online payment was successful"));
        outputStream.reset();
        PaymentNotification paymentSms = new OnlinePaymentNotification(new SmsChannel());
        paymentSms.notifyCustomer();
        assertTrue(outputStream.toString().contains("online payment was successful"));
    }

    @Test
    void sameChannelWorksWithDifferentPayments() {
        NotificationChannel email = new EmailChannel();
        PaymentNotification online = new OnlinePaymentNotification(email);
        PaymentNotification cash = new CashOnDeliveryPayment(email);
        PaymentNotification bitcoin = new BitcoinPayment(email);
        online.notifyCustomer();
        cash.notifyCustomer();
        bitcoin.notifyCustomer();
        String output = outputStream.toString();
        assertTrue(output.contains("online payment was successful"));
        assertTrue(output.contains("cash on delivery order"));
        assertTrue(output.contains("Bitcoin payment"));
    }

    @Test
    void constructorStoresChannelReferenceCorrectly() {
        NotificationChannel channel = new EmailChannel();
        PaymentNotification payment = new OnlinePaymentNotification(channel);
        assertNotNull(payment);
        payment.notifyCustomer();
        assertTrue(outputStream.toString().contains("[Email] "));
    }

    @Test
    void nullChannel_throwsNullPointerException() {
        PaymentNotification payment = new OnlinePaymentNotification(null);
        assertThrows(NullPointerException.class, payment::notifyCustomer);
    }

    @Test
    void fullBridgeScenario_runsWithoutErrors() {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel push = new PushChannel();
        PaymentNotification[] payments = {
                new OnlinePaymentNotification(email),
                new OnlinePaymentNotification(sms),
                new CashOnDeliveryPayment(email),
                new CashOnDeliveryPayment(sms),
                new BitcoinPayment(email),
                new BitcoinPayment(sms),
                new BitcoinPayment(push)
        };
        assertDoesNotThrow(() -> {
            for (PaymentNotification p : payments) {
                p.notifyCustomer();
            }
        });
        String output = outputStream.toString();
        assertTrue(output.contains("[Email]"));
        assertTrue(output.contains("[SMS]"));
        assertTrue(output.contains("[Push Notification]"));
    }

    @BeforeEach
    void tearDown() {
        System.setOut(originalOut);
    }
}
