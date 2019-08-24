import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Formats the list of tasks.
     * @return formatted list of tasks
     */
    public String getTasksInString() {
        StringBuilder myStringBuilder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i < taskList.size() - 1) {
                myStringBuilder.append(i + 1).append(".").append(taskList.get(i)).append("\n")
                        .append(Messages.COMMAND_INDENTATION);
            } else if (i == taskList.size() - 1) {
                myStringBuilder.append(i + 1).append(".").append(taskList.get(i));
            }
        }
        return myStringBuilder.toString();
    }

    public void addToTaskList(Task task) {
        taskList.add(task);
    }

    public int getSize() {
        return taskList.size();
    }

    public Task deleteFromTaskList(int index) throws IndexOutOfBoundsException {
        return taskList.remove(index);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
