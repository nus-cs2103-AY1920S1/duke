import java.util.ArrayList;

public class ItemsList {
    private ArrayList<Task> itemsList;

    public ItemsList() {
        itemsList = new ArrayList<>();
    }

    public void addItem(Task task) {
        itemsList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task);
        System.out.printf("Now you have %d tasks in the list.\n", itemsList.size());
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
