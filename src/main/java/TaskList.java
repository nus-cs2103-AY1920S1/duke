import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    public List<String> getTaskNames() {
        List<String> taskNames = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            taskNames.add( (i + 1) + ". " + tasks.get(i));
        }
        return taskNames;
    }
}
