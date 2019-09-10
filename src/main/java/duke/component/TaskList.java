package duke.component;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * Represents a collection of Tasks tracked by the Duke application with implementation of operations on the task list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a <code>TaskList</code> object that stores <code>Task</code> objects in an ordered list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Retrieves a <code>Task</code> with a given task ID from this <code>TaskList</code>.
     * 
     * @param id <code>int</code> ID of the task.
     * @return the <code>Task</code> with the given ID.
     */
    public Task getTask(int id) {
        assert id >= 1 && id <= this.numberOfTasks() : "Attempting to retrieve non-existent Task!";

        return this.tasks.get(id - 1);
    }

    /**
     * Inserts a <code>Task</code> as the next entry in this <code>TaskList</code>.
     * 
     * @param task the <code>Task</code> to be inserted.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a <code>Task</code> with a given task ID from this <code>TaskList</code> and returns it.
     * 
     * @param id <code>int</code> ID of the task.
     * @return the <code>Task</code> that was removed.
     */
    public Task deleteTask(int id) {
        assert id >= 1 && id <= this.numberOfTasks() : "Attempting to delete non-existent Task!";

        return this.tasks.remove(id - 1);
    }

    /**
     * Tags a <code>Task</code> with a given <code>String</code> then returns it.
     * 
     * @param id <code>int</code> ID of the task.
     * @param tag <code>String</code> tag to apply to this <code>Task</code>.
     * @return the <code>Task</code> that was updated.
     */
    public Task tagTask(int id, String tag) {
        assert id >= 1 && id <= this.numberOfTasks() : "Attempting to tag non-existent Task!";

        Task updatedTask = this.tasks.get(id - 1);
        updatedTask.addTag(tag);
        return updatedTask;
    }

    /**
     * Returns all <code>Task</code> objects whose descriptions contain a search string in an <code>ArrayList</code>.
     * 
     * @param searchString <code>String</code> to search <code>Task</code> descriptions with.
     * @return an <code>ArrayList</code> of <code>Task</code> objects which match the search string.
     */
    public ArrayList<Task> searchByDescription(String searchString) {
        return this.tasks
            .stream()
            .filter(task -> task.getDescription().contains(searchString))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns all <code>Task</code> objects which contains the supplied tag in an <code>ArrayList</code>.
     * 
     * @param tagString exact <code>String</code> to search <code>Task</code> tags with.
     * @return an <code>ArrayList</code> of <code>Task</code> objects which matches the supplied tag.
     */
    public ArrayList<Task> searchByTag(String tagString) {
        return this.tasks
            .stream()
            .filter(task -> task.containsTag(tagString))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns the number of tasks in this <code>TaskList</code>.
     * 
     * @return an <code>int</code> number of tasks.
     */
    public int numberOfTasks() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        // Retrieve the length of the task list and the number of digits of the maximum task ID
        int length = this.numberOfTasks();

        if (length == 0) {
            return "Your task list is empty!";
        } else {
            // Build format string to print all task IDs with the same width, left-padded with spaces
            long numDigits = Math.round(Math.log10(length) + 0.5);
            String formatTemplate = "%1$" + numDigits + "s";

            for (int i = 0; i < length; i++) {
                result.append(String.format(formatTemplate, i + 1))
                      .append(String.format(". %s\n", this.tasks.get(i).toString()));
            }

            // Trim trailing newline
            result.setLength(result.length() - 1);
            return result.toString();
        }
    }
}
