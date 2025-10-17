package decorator;

public class LoyaltyDiscount {

    public double applyDiscount(double total, String status) {
        switch (status.toLowerCase()) {
            case "silver":  return total * 0.95;  // 5% off
            case "gold":    return total * 0.90;  // 10% off
            case "platinum":return total * 0.85;  // 15% off
            default:        return total;
        }
    }
}
