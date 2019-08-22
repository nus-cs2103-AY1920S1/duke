import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    protected ArrayList<Task> getTasks() {
        return this.tasks;
    }

    protected int getTaskCount() {
        return this.tasks.size();
    }

    protected boolean addTask(Task task) {
        return this.tasks.add(task);
    }
}
