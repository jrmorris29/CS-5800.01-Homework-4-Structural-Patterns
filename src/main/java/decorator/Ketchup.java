package decorator;

public class Ketchup extends ToppingDecorator {
    public Ketchup(FoodItem baseItem) {
        super(baseItem);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.25;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Ketchup";
    }
}
