import java.util.ArrayList;
import java.util.ListIterator;

public class TodoList {
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

    int length() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListIterator<Task> listIterator = this.list.listIterator();
        while(listIterator.hasNext()) {
            sb.append(listIterator.nextIndex() + 1);
            sb.append(".");
            sb.append(listIterator.next());
            sb.append("\n");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }
}
