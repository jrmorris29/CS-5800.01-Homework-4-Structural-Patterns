package decorator;

public class Cheese extends ToppingDecorator {
    public Cheese(FoodItem baseItem) {
        super(baseItem);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.75;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Cheese";
    }
}
