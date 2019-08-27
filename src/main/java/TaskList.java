import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int index) {
        Task t = tasks.remove(index);
        return t;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}
