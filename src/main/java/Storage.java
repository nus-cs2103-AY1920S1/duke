import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> items = new ArrayList<Task>();

    protected ArrayList<Task> retrieve() {
        return this.items;
    }

    protected boolean addTask(String title) {
        Task newTask = new Task(title);
        return this.items.add(newTask);
    }
}
