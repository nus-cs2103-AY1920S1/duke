import java.util.ArrayList;

/**
 * TaskList class that abstract the ArrayList Task object.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public int getTaskSize() {
        return list.size();
    }

    public ArrayList<Task> getlist() {
        return this.list;
    }

    public Task getTaskByIndex(int index) {
        return this.list.get(index - 1);
    }

    public void markDone(int index) {
        Task task = list.get(index - 1);
        task.markAsDone();
    }

    public Task removeTaskByIndex(int index) {
        return list.remove(index - 1);
    }
}


