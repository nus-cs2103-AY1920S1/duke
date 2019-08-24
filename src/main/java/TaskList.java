import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    List<Task> getList() {
        return this.list;
    }

    void add(Task task) {
        this.list.add(task);
    }

    int getSize() {
        return this.list.size();
    }

    Task getTask(int ordering) {
        return this.list.get(ordering - 1);
    }

    void remove(int ordering) {
        this.list.remove(ordering - 1);
    }
}
