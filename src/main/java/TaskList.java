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

    void clear() {
        this.list.clear();
    }
}
