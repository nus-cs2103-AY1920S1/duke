import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public Task getTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return null;
        }

        return taskList.get(index);
    }

    public void printTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.format(" %d.%s", i + 1, taskList.get(i));
            System.out.println(output);
        }
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
