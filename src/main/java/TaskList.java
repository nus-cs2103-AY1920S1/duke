import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void markTaskAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }
}
