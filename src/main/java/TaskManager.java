import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    List<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public void doneTask(int i) {
        taskList.get(i).changeStatusTrue();
    }

    public Task deleteTask(int i) {
        return taskList.remove(i);
    }

}
