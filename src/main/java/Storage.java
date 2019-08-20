import java.util.ArrayList;

public class Storage {
    private ArrayList<String> items = new ArrayList<String>();

    protected ArrayList<String> retrieve() {
        return this.items;
    }

    protected boolean addItem(String item) {
        return this.items.add(item);
    }
}
