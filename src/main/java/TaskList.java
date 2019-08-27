import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> storage) {
        this.tasks = storage;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public void remove(int i) {
        tasks.remove(i);
    }
}
