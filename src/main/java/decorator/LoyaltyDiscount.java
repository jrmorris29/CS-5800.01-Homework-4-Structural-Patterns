package decorator;

public class LoyaltyDiscount {

    public double applyDiscount(double total, String status) {
        switch (status.toLowerCase()) {
            case "silver":  return total * 0.95;
            case "gold":    return total * 0.90;
            case "platinum":return total * 0.85;
            default:        return total;
        }
    }
}
