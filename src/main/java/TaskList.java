import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTaskAtIndex(int index) {
        return tasks.get(index - 1);
    }

    public void setTaskAtIndexDone(int index) throws IndexOutOfBoundsException{
        tasks.get(index - 1).setDone();
    }

    public Task removeTaskAtIndex(int index) {
        return tasks.remove(index - 1);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

}
