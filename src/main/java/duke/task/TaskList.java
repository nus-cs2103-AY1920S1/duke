package duke.task;

import duke.shared.Messages;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
     * @param listToDisplay list of tasks to be formatted
     * @return formatted list of tasks
     */
    public String getTasksInString(List<Task> listToDisplay) {
        StringBuilder myStringBuilder = new StringBuilder();
        for (int i = 0; i < listToDisplay.size(); i++) {
            if (i < listToDisplay.size() - 1) {
                myStringBuilder.append(i + 1).append(".").append(listToDisplay.get(i)).append("\n")
                        .append(Messages.COMMAND_INDENTATION);
            } else if (i == listToDisplay.size() - 1) {
                myStringBuilder.append(i + 1).append(".").append(listToDisplay.get(i));
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

    /**
     * Finds tasks that contains the keywords stated in the itemToFind
     * @param itemToFind keywords provided by the user
     * @return list of tasks that matches the keywords
     */
    public List<Task> findTasks(String[] itemToFind) {
        List<Task> matchedTaskList = new ArrayList<>();
        List<String> itemListInString = Arrays.asList(itemToFind);
        for (Task task : taskList) {
            List<String> subNameList = Arrays.asList(task.getName().split("\\s+"));
            if (!Collections.disjoint(itemListInString, subNameList)) {
                matchedTaskList.add(task);
            }
        }
        return matchedTaskList;
    }
}
