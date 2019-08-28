import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> listOfTask;

    public TaskList() {
        this.listOfTask = new ArrayList<Task>();
    }

    public void add(Task task) {
        listOfTask.add(task);
    }

    public int size() {
        return listOfTask.size();
    }

    public Task get(int index) {
        return listOfTask.get(index);
    }

    public void remove(int index) {
        listOfTask.remove(index);
    }
}
