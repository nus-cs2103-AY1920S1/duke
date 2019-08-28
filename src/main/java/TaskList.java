import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public boolean add(Task task) {
        return tasks.add(task);
    }

    public Task remove(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }
}
