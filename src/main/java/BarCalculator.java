import java.util.ArrayList;
import java.util.List;

public class BarCalculator {

    private final int numberOfPerson;

    private final List<Item> items = new ArrayList<>();

    public BarCalculator(int numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return List.copyOf(items);
    }

    public double getMoneyForOnePerson() {
        return items.stream().mapToDouble(Item::cost).sum() / numberOfPerson;
    }
}
