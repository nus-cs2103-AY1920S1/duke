import java.util.ArrayList;

public class ItemsList {
    private ArrayList<Task> itemsList;

    public ItemsList() {
        itemsList = new ArrayList<>();
    }

    public void addItem(Task task) {
        itemsList.add(task);
        System.out.println("added: " + task);
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < itemsList.size(); i++) {
            Task task = itemsList.get(i);
            System.out.printf("%d.[%s] %s\n", (i + 1), task.getStatusIcon(), task.description);
        }
    }

    public Task getTaskAtIndex(int position) {
        return itemsList.get(position);
    }

}
