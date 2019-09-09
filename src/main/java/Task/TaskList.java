package Task;

import Exceptions.DukeException;
import Exceptions.InvalidItemException;
import UI.MessageGenerator;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    List<Task> taskList = new ArrayList<>();
    MessageGenerator msgGenerator = new MessageGenerator();

    /**
     * Creates task list and adds a placeholder value for easier tracking.
     */
    public TaskList() {
        taskList.add(null);
    }

    /**
     * @return Number of tasks in list.
     */
    private int noTasks() {
        return taskList.size() - 1;
    }

    /**
     * Adds task to task list.
     * Prints message when task is added via user input.
     *
     * @param task task to be added.
     * @return program message when task is added.
     */
    public String addTask(Task task) {
        taskList.add(task);
        return msgGenerator.getAddMessage(task, noTasks());
    }

    /**
     * Adds task to task List when task is loaded from the file.
     *
     * @param task task loaded from the file that is to be added.
     */
    public void loadTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task of given identification number from list.
     * Prints message when task is removed.
     * Method is only called when task is a valid number.
     *
     * @param taskNo identification number of task in list.
     * @return message to indicate task is removed.
     */
    private String removeTask(int taskNo) {
        Task deleted = taskList.get(taskNo);
        taskList.remove(taskNo);
        return msgGenerator.getRemoveMessage(deleted, noTasks());
    }

    /**
     * Formats task list into list of strings for printing/writing.
     *
     * @return List of Strings.
     */
    private List<String> listify(List<Task> tasks) {
        List<String> list = new ArrayList<String>();
        for (Task task: tasks) {
            if (task != null) {
                list.add(task.toString());
            }
        }
        return list;
    }

    /**
     * @return task list.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Prints list of tasks using the message generator.
     */
    public String getList() {
        return msgGenerator.getList(listify(this.taskList));
    }

    /**
     * Checks if given task number exists in task list.
     *
     * @param taskNo identification number for task.
     * @return whether or not task number exists.
     */
    private boolean invalidTaskNo(int taskNo) {
        return taskNo >= taskList.size();
    }

    /**
     * Marks a task as done and returns the done message.
     *
     * @param taskNo identification number for task.
     * @return done message.
     */
    public String setDone(int taskNo) {
        try {
            if (invalidTaskNo(taskNo)) {
                throw new InvalidItemException();
            }
            taskList.get(taskNo).setDone();
            return msgGenerator.getDoneMessage(taskList.get(taskNo));
        } catch (DukeException e) {
            return e.getErrorMessage();
        }
    }

    /**
     * Deletes task with given identification number.
     *
     * @param taskNo identification number for task.
     * @return String with delete message.
     */
    public String deleteTask(int taskNo) {
        try {
            if (invalidTaskNo(taskNo)) {
                throw new InvalidItemException();
            }
            assert taskList.contains(taskList.get(taskNo));
            return removeTask(taskNo);
        } catch (DukeException e) {
            return e.getErrorMessage();
        }
    }

    /**
     * Searches and prints matching tasks in task list
     * .
     * @param keyword word that task must contain to be printed.
     * @return String containing list of tasks.
     */
    public String findMatchingTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task != null && task.getTaskInfo().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return msgGenerator.getMatchingList(listify(matchingTasks));
    }

}
