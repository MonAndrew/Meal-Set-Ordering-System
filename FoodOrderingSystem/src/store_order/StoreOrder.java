package store_order;

/**
 *
 * @author Junwel
 */
import java.util.ArrayList;
import java.util.List;

public class StoreOrder {

    private List<StoreItem> items = new ArrayList<>();

    public void addItem(StoreItem newItem) {

        for (StoreItem item : items) {

            if (item.getName().equals(newItem.getName())) {

                int newQty = item.getQuantity() + newItem.getQuantity();

                items.remove(item);

                items.add(new StoreItem(
                        item.getName(),
                        item.getPrice(),
                        newQty
                ));

                return;
            }
        }

        items.add(newItem);
    }

    public List<StoreItem> getItems() {
        return items;
    }

    public double getTotal() {

        double total = 0;

        for (StoreItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void clear() {
        items.clear();
    }
}
