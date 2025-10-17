package decorator;

public class Onions extends ToppingDecorator {
    public Onions(FoodItem baseItem) {
        super(baseItem);
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Onions";
    }
}
