import java.util.ArrayList;

public class Task {
    private String description;
    private static ArrayList<Task> taskList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        taskList.add(this);
    }

    @Override
    public String toString() {
        return this.description;
    }

    public static void printList() {
        int counter = 1;
        for (Task task : taskList) {
            System.out.println(counter++ + ". " + task);
        }
    }
}
