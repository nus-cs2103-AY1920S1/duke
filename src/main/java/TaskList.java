import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
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

    public Task deleteTask(int index) throws OutOfBoundsDeletionException {
        try {
            return taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfBoundsDeletionException("No task with index number " + (index + 1) + " on your tasklist!");
        }
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public List<Task> getMatchedTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.toString().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }
}
