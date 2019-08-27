import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public TaskList search(String str) {
        return new TaskList(this.tasks.stream().filter(t -> t.description.contains(str)).collect(Collectors.toList()));
    }
}
