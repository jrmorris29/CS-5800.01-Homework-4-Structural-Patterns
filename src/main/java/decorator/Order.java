package decorator;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<FoodItem> items = new ArrayList<>();

    public void addItem(FoodItem item) {
        items.add(item);
    }

    public double getTotalCost() {
        return items.stream().mapToDouble(FoodItem::getCost).sum();
    }

    public String getOrderSummary() {
        StringBuilder summary = new StringBuilder("Order includes:\n");
        for (FoodItem item : items) {
            summary.append("- ").append(item.getDescription())
                    .append(" ($").append(String.format("%.2f", item.getCost()))
                    .append(")\n");
        }
        summary.append("Total before discount: $")
                .append(String.format("%.2f", getTotalCost()));
        return summary.toString();
    }

    public List<FoodItem> getItems() {
        return items;
    }
}
