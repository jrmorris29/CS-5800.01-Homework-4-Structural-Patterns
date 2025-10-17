package decorator;

public class HotDog implements FoodItem {
    @Override
    public double getCost() {
        return 3.75;
    }

    @Override
    public String getDescription() {
        return "Hot Dog";
    }
}
