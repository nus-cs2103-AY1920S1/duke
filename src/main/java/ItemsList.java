import java.util.ArrayList;

public class ItemsList {
    private ArrayList<String> itemsList;

    public ItemsList() {
        itemsList = new ArrayList<>();
    }

    public void addItem(String item) {
        itemsList.add(item);
        System.out.println("added: " + item);
    }

    public void printList() {
        for (int i = 0; i < itemsList.size(); i++) {
            System.out.println((i + 1) + ". " + itemsList.get(i));
        }
    }

}
