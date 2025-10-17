package decorator;

public class driver {
    public static void main(String[] args) {
        System.out.println("=== Restaurant Order System (Decorator Pattern) ===");

        FoodItem burger = new Burger();
        burger = new Cheese(new Onions(new Ketchup(burger)));

        FoodItem fries = new Fries();
        fries = new Ketchup(fries);

        Order order = new Order();
        order.addItem(burger);
        order.addItem(fries);

        System.out.println(order.getOrderSummary());

        LoyaltyDiscount discount = new LoyaltyDiscount();
        double discountedTotal = discount.applyDiscount(order.getTotalCost(), "Gold");
        System.out.printf("After Gold loyalty discount: $%.2f%n", discountedTotal);
    }
}
