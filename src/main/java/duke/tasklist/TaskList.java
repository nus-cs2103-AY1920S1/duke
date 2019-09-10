package duke.tasklist;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Contains the task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list if there is no previous session of Duke, or no tasks in the task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Fills the new  task list with tasks from previous session of Duke.
     *
     * @param savedTasks Task list from the previous session of Duke.
     */
    public TaskList(ArrayList<Task> savedTasks) {
        tasks = new ArrayList<>();
        tasks.addAll(savedTasks);
    }

    /**
     * Retrieves the task list.
     *
     * @return Task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Displays the contents of the task list to the user.
     */
    public String printTasks() {
        if (tasks.isEmpty()) {
            return "You do not have any tasks in your list.\n" + "Use 'help' to see how to add tasks" +
                    " to your list!";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + "." + tasks.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Marks the task as completed and informs the user.
     *
     * @param taskNumber Index of the task to be marked as completed.
     */
    public String completeTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
        return "Nice! I've marked this task as done:\n" + tasks.get(taskNumber - 1).toString();
    }

    /**
     * Deletes the task an informs the user.
     *
     * @param taskNumber Index of the task to be deleted.
     */
    public String deleteTask(int taskNumber) {
        String taskDescription = tasks.get(taskNumber - 1).toString();
        tasks.remove(taskNumber - 1);
        return "Noted. I've removed this task:\n" + taskDescription + "\n" + getListSize();
    }

    /**
     * Searches for tasks matching the search term and displays the matching tasks to the user.
     *
     * @param searchTerm Key word/phrase to search for.
     */
    public String findTasks(String searchTerm) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                searchResults.add(task);
            }
        }

        if (searchResults.isEmpty()) {
            return "OOPS!!! You don't have any tasks containing the term \"" + searchTerm + "\".";
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < searchResults.size(); i++) {
            sb.append((i + 1) + "." + searchResults.get(i) + "\n");
        }
        return sb.toString();
    }

    /**
     * Adds the task to the task list.
     *
     * @param task Task to be added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return "Got it. I've added this task:\n" + task.toString() + "\n" + getListSize();
    }

    /**
     * Informs users of the size of their task list.
     *
     * @return Number of tasks in the list.
     */
    public String getListSize() {
        if (tasks.size() == 1) {
            return "Now you have 1 task in the list.";
        }

        return "Now you have " + tasks.size() + " tasks in the list.";
    }

}