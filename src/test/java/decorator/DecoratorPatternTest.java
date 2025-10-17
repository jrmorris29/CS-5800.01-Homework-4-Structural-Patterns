package decorator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratorPatternTest {

    private LoyaltyDiscount discount;

    @BeforeEach
    void setup() {
        discount = new LoyaltyDiscount();
    }

    @Test
    void burgerBaseCostAndDescription() {
        FoodItem burger = new Burger();
        assertEquals(5.00, burger.getCost(), 0.001);
        assertEquals("Burger", burger.getDescription());
    }

    @Test
    void friesBaseCostAndDescription() {
        FoodItem fries = new Fries();
        assertEquals(2.50, fries.getCost(), 0.001);
        assertEquals("Fries", fries.getDescription());
    }

    @Test
    void hotdogBaseCostAndDescription() {
        FoodItem hotdog = new HotDog();
        assertEquals(3.75, hotdog.getCost(), 0.001);
        assertEquals("Hot Dog", hotdog.getDescription());
    }

    @Test
    void addKetchupToBurger() {
        FoodItem item = new Ketchup(new Burger());
        assertEquals(5.25, item.getCost(), 0.001);
        assertTrue(item.getDescription().contains("Ketchup"));
    }

    @Test
    void addCheeseAndOnionsToFries() {
        FoodItem item = new Onions(new Cheese(new Fries()));
        assertEquals(3.75, item.getCost(), 0.001);
        assertTrue(item.getDescription().contains("Onions"));
    }

    @Test
    void multipleToppingsChainWorks() {
        FoodItem item = new Cheese(new Onions(new Ketchup(new HotDog())));
        assertEquals(5.25, item.getCost(), 0.001);
        assertTrue(item.getDescription().contains("Cheese"));
        assertTrue(item.getDescription().contains("Onions"));
        assertTrue(item.getDescription().contains("Ketchup"));
    }

    @Test
    void orderTotalCalculation() {
        Order order = new Order();
        order.addItem(new Burger());
        order.addItem(new Fries());
        assertEquals(7.50, order.getTotalCost(), 0.001);
    }

    @Test
    void orderSummaryIncludesItems() {
        Order order = new Order();
        order.addItem(new Cheese(new Burger()));
        String summary = order.getOrderSummary();
        assertTrue(summary.contains("Cheese"));
        assertTrue(summary.contains("Total before discount"));
    }

    @Test
    void silverDiscountAppliedCorrectly() {
        assertEquals(95.0, discount.applyDiscount(100.0, "Silver"), 0.001);
    }

    @Test
    void goldDiscountAppliedCorrectly() {
        assertEquals(90.0, discount.applyDiscount(100.0, "Gold"), 0.001);
    }

    @Test
    void platinumDiscountAppliedCorrectly() {
        assertEquals(85.0, discount.applyDiscount(100.0, "Platinum"), 0.001);
    }

    @Test
    void invalidStatusNoDiscount() {
        assertEquals(100.0, discount.applyDiscount(100.0, "None"), 0.001);
    }

    @Test
    void fullSystemIntegration() {
        FoodItem burger = new Cheese(new Ketchup(new Burger()));
        FoodItem fries = new Onions(new Fries());
        Order order = new Order();
        order.addItem(burger);
        order.addItem(fries);
        LoyaltyDiscount loyalty = new LoyaltyDiscount();

        double total = order.getTotalCost();
        double discounted = loyalty.applyDiscount(total, "Gold");

        assertTrue(total > discounted);
        assertEquals(total * 0.9, discounted, 0.001);
    }
}
