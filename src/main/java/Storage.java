import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Task> list;

    Storage() {
        this.list = new ArrayList<>();
    }

    List<Task> getList() {
        return this.list;
    }

    void add(Task task) {
        this.list.add(task);
    }
}
