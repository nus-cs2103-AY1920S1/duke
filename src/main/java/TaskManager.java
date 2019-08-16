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

    public Task getTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return null;
        }

        return taskList.get(index);
    }

    public void markAsDone(int taskIndex) {
        Task task = getTask(taskIndex);

        if (task != null) {
            task.markAsDone();
        }

        System.out.println(" Nice! I've marked this task as done: ");
        System.out.println(" " + task);
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
