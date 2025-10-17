package decorator;

public class Burger implements FoodItem {
    @Override
    public double getCost() {
        return 5.00;
    }

    @Override
    public String getDescription() {
        return "Burger";
    }
}
