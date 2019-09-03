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
     *
     * @param ui User interface that assists with printing.
     */
    public void printTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.show("You do not have any tasks in your list.");
            ui.show("Use 'help' to see how to add tasks to your list!");
        } else {
            ui.show("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.show((i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Marks the task as completed and informs the user.
     *
     * @param taskNumber Index of the task to be marked as completed.
     * @param ui User interface that assists with printing.
     */
    public void completeTask(int taskNumber, Ui ui) {
        tasks.get(taskNumber - 1).markAsDone();
        ui.show("Nice! I've marked this task as done:");
        ui.show(tasks.get(taskNumber - 1).toString());
    }

    /**
     * Deletes the task an informs the user.
     *
     * @param taskNumber Index of the task to be deleted.
     * @param ui User interface that assists with printing.
     */
    public void deleteTask(int taskNumber, Ui ui) {
        String taskDescription = tasks.get(taskNumber - 1).toString();
        tasks.remove(taskNumber - 1);
        ui.show("Noted. I've removed this task:");
        ui.show(taskDescription);
        ui.show(getListSize());
    }

    /**
     * Searches for tasks matching the search term and displays the matching tasks to the user.
     *
     * @param searchTerm Key word/phrase to search for.
     * @param ui User interface that assists with printing.
     */
    public void findTasks(String searchTerm, Ui ui) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(searchTerm)) {
                searchResults.add(task);
            }
        }
        if (searchResults.isEmpty()) {
            ui.show("OOPS!!! You don't have any tasks containing the term \"" + searchTerm + "\".");
        } else {
            ui.show("Here are the matching tasks in your list:");
            for (int i = 0; i < searchResults.size(); i++) {
                ui.show((i + 1) + "." + searchResults.get(i));
            }
        }
    }

    /**
     * Adds the task to the task list.
     *
     * @param task Task to be added.
     * @param ui User interface that assists with printing.
     */
    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.show("Got it. I've added this task:");
        ui.show(task.toString());
        ui.show(getListSize());
    }

    /**
     * Informs users of the size of their task list.
     *
     * @return Number of tasks in the list.
     */
    public String getListSize() {
        if (tasks.size() == 1) {
            return "Now you have 1 task in the list.";
        } else {
            return "Now you have " + tasks.size() + " tasks in the list.";
        }
    }

}