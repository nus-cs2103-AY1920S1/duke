import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getIndex(int index) {
        return tasks.get(index);
    }

    public int numberOfTasks() {
        return this.tasks.size();
    }

    public void deleteTaskAt(int index) {
        this.tasks.remove(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }
}
