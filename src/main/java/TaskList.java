import java.util.ArrayList;
import java.util.List;

public class TaskList implements MyList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    //adds a task to the list
    @Override
    public void add(Task task) {
        list.add(task);
    }

    //returns the list of Strings
    @Override
    public List<Task> getList() {
        return list;
    }

    @Override
    public int getNumTasks() {
        return list.size();
    }

    //index is the number shown when list is called
    @Override
    public Task getTask(int index) {
        return list.get(index - 1);
    }

    @Override
    public Task removeTask(int index) {
        return list.remove(index - 1);
    }
}
