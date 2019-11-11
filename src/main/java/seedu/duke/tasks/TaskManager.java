package seedu.duke.tasks;

import seedu.duke.tasks.exceptions.DuplicateTaskException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;
    private TaskStorage taskStorage;

    /**
     * Constructor for <code>TaskManager</code>, which handles execution involving tasks that are
     * stored by Duke chat bot.
     *
     * @param filePath File location to save tasks data that are stored by Duke chat bot.
     * @throws Exception If Duke chat bot fails to load initial data.
     */
    public TaskManager(String filePath) throws Exception {
        taskStorage = new TaskStorage(filePath);
        tasks = taskStorage.loadTasks();

        List<Long> listIDs = tasks.stream().map(Task::getTaskID).collect(Collectors.toList());
        TaskIdGenerator.shared.setInitialListIDs(listIDs);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets a task with a specified ID.
     *
     * @param taskID ID of the task
     * @return A task with the specified ID.
     */
    public Task getTaskWithID(long taskID) {
        return tasks.get(getTaskIndexWithID(taskID));
    }

    /**
     * Adds a new task to the list of tasks that is stored by Duke chat bot.
     *
     * @param task A new task to be added into the list.
     * @throws DuplicateTaskException If there is a duplicate task in the list.
     * @throws IOException            If Duke chat bot fails to save the changes into the database file.
     */
    public void addTask(Task task) throws DuplicateTaskException, IOException {
        if (tasks.stream().anyMatch(task::equals)) {
            throw new DuplicateTaskException("Failed to add task, duplicate detected");
        }
        tasks.add(task);
        taskStorage.saveTasks(tasks);
    }

    /**
     * Removes a task from the list of tasks that is stored by Duke chat bot.
     *
     * @param taskID The ID of the task in the list that will be removed.
     * @return The task that has been removed.
     * @throws IOException An error if Duke chat bot fails to save the changes to the data file.
     */
    public Task removeTask(long taskID) throws IOException {
        Task removedTask = tasks.remove(getTaskIndexWithID(taskID));
        taskStorage.saveTasks(tasks);
        return removedTask;
    }

    /**
     * Marks a task that is stored by Duke chat bot as done.
     *
     * @param taskID The ID of the task in the list that will be removed.
     * @throws IOException An error if Duke chat bot fails to save the changes to the data file.
     */
    public void markAsDone(long taskID) throws IOException {
        tasks.get(getTaskIndexWithID(taskID)).markAsDone();
        taskStorage.saveTasks(tasks);
    }

    /**
     * Find tasks that match the specified keyword.
     *
     * @param keyword A keyword that is searched in the task description.
     * @return List of tasks that match the specified keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> foundList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundList.add(task);
            }
        }
        return foundList;
    }

    private int getTaskIndexWithID(long id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskID() == id) {
                return i;
            }
        }
        return -1;
    }
}
