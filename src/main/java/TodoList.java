import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class TodoList implements Serializable {
    private ArrayList<Task> list;

    TodoList() {
        this.list = new ArrayList<>(100);
    }

    void add(Task task) {
        list.add(task);
    }

    Task markAsDone(int index) {
        Task task = list.get(index - 1);
        task.markAsDone();
        return task;
    }

    Task delete(int index) {
        return list.remove(index - 1);
    }

    int length() {
        return list.size();
    }

    @Override
    public String toString() {
        if (this.list.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        ListIterator<Task> listIterator = this.list.listIterator();
        while (listIterator.hasNext()) {
            sb.append(listIterator.nextIndex() + 1);
            sb.append(".");
            sb.append(listIterator.next());
            sb.append("\n");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    List<Task> find(String description) {
        return  this.list.stream()
                .filter(task -> task.toString().contains(description))
                .collect(Collectors.toList());
    }
}
