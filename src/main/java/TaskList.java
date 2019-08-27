import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> myList) {
        this.taskList = myList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("Task does not exist");
        }
        taskList.remove(index);
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("Task does not exist");
        }
        return taskList.get(index);
    }

    public int size() {
        return taskList.size();
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
