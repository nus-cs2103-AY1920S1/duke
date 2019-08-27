import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    protected List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String list() {
        String message = "";
        int index = 1;
        for (Iterator iterator = taskList.iterator(); iterator.hasNext(); index++) {
            message = message + index + "." + iterator.next() + "\n";
        }
        return message;
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Iterator iterator() {
        return taskList.iterator();
    }
}
