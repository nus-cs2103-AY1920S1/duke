import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public Task remove(int position) {
        Task deleted = list.remove(position - 1);
        return deleted;
    }

    public Task get(int position) {
        return this.list.get(position - 1);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void markAsDone(int position) {
        Task task = this.get(position);
        task.done();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println(String.format("     %s", task));
    }
}
