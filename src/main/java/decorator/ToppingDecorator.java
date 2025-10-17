package decorator;

public abstract class ToppingDecorator implements FoodItem {
    protected FoodItem baseItem;

    public ToppingDecorator(FoodItem baseItem) {
        this.baseItem = baseItem;
    }

    @Override
    public double getCost() {
        return baseItem.getCost();
    }

    @Override
    public String getDescription() {
        return baseItem.getDescription();
    }
}
