package duke.component;

import java.lang.StringBuilder;
import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     *  Constructs a <code>TaskList</code> object that stores <code>Task</code> objects in an ordered list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     *  Retrieves a <code>Task</code> with a given task ID from this <code>TaskList</code>.
     *  @param id <code>int</code> ID of the task.
     *  @return the <code>Task</code> with the given ID.
     */
    public Task getTask(int id) {
        return this.tasks.get(id - 1);
    }

    /**
     *  Inserts a <code>Task</code> as the next entry in this <code>TaskList</code>.
     *  @param task the <code>Task</code> to be inserted.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     *  Removes a <code>Task</code> with a given task ID from this <code>TaskList</code> and returns it.
     *  @param id <code>int</code> ID of the task.
     *  @return the <code>Task</code> that was removed.
     */
    public Task deleteTask(int id) {
        return this.tasks.remove(id - 1);
    }

    /**
     *  Returns all <code>Task</code> objects whose descriptions contain a search string in an <code>ArrayList</code>.
     *  @param searchString <code>String</code> to search <code>Task</code> descriptions with.
     *  @return an <code>ArrayList</code> of <code>Task</code> objects which match the search string.
     */
    public ArrayList<Task> searchTask(String searchString) {
        ArrayList<Task> results = new ArrayList<Task>();
        for (Task task: this.tasks) {
            if (task.getDescription().contains(searchString)) {
                results.add(task);
            }
        }
        return results;
    }

    /**
     *  Returns the number of tasks in this <code>TaskList</code>.
     *  @return an <code>int</code> number of tasks.
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
