package duke.core;

import duke.tasks.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private List<Task> taskList;

    /**
     * Class constructor.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Overloaded class constructor for existing lists of Tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }


    /**
     * Load data from input list of Tasks.
     * @param loadedTaskList Starting list of Tasks
     */
    public void loadData(List<Task> loadedTaskList) {
        taskList = loadedTaskList;
    }

    /**
     * Return list of Tasks.
     */
    public List<Task> getTasks() {
        return taskList;
    }

    /**
     * Clear list of Tasks.
     */
    public void clearList() {
        taskList.clear();
    }

    /**
     * Return number of Tasks.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Return Task at input index.
     * @param index Index of Task to be retrieved
     * @throws IndexOutOfBoundsException Index out of bounds exception
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    /**
     * Add Task into TaskList.
     * @param task Task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Return matching Tasks from TaskList.
     * @param keyword Keyword to be matched
     */
    public List<Task> getMatchingTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task k : taskList) {
            if (k.checkMatch(keyword)) {
                matchingTasks.add(k);
            }
        }
        return matchingTasks;
    }

    /**
     * Mark a specific Task as complete.
     * @param entryNumber Entry number of Task to be marked as complete (Starting from 1 onwards)
     * @throws IndexOutOfBoundsException Index out of bounds exception
     */
    public Task completeTask(int entryNumber) throws IndexOutOfBoundsException {
        Task taskToComplete = this.getTask(entryNumber - 1);
        taskToComplete.setDone();
        return taskToComplete;
    }

    /**
     * Delete Task from TaskList.
     * @param entryNumber Entry number of Task to be deleted (Starting from 1 onwards)
     * @throws IndexOutOfBoundsException Index out of bounds exception
     */
    public Task deleteTask(int entryNumber) throws IndexOutOfBoundsException {
        return taskList.remove(entryNumber - 1);
    }
}
