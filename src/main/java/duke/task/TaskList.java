package duke.task;

import duke.shared.Messages;
import duke.task.Task;

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

    /**
     * Adds task to tasklist.
     * @param task task to be added to tasklist
     */
    public void addToTaskList(Task task) {
        taskList.add(task);
    }

    /**
     * Provides the number of items in taskList.
     * @return number of items inside taskList
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Removes task from taskList.
     * @param index index of the taskList
     * @return removed task
     * @throws IndexOutOfBoundsException index less than 0 or bigger than the size of taskList
     */
    public Task deleteFromTaskList(int index) throws IndexOutOfBoundsException {
        return taskList.remove(index);
    }

    /**
     * Retrieves task from tasklist.
     * @param index index of the taskList
     * @return requested task
     * @throws IndexOutOfBoundsException index less than 0 or bigger than the size of the tasklist
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    /**
     * Provides all tasks within the tasklist.
     * @return a list of tasks within the tasklist
     */
    public List<Task> getTaskList() {
        return taskList;
    }
}
