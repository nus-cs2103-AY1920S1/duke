import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(String numString) {
        int numInt = Integer.valueOf(numString);
        return list.remove(numInt - 1);
    }
}
