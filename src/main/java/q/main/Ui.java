package q.main;

import q.error.QException;
import q.task.Task;

/**
 * Handles User Interface.
 */
public class Ui {

    /**
     * Constructs a Ui.
     */
    public Ui() {
    }

    /**
     * Returns a formatted string for printing.
     *
     * @param s String to be printed.
     * @return A string formatted for printing.
     */
    private String print(String s) {
        return s + "\n";
    }

    /**
     * Returns an error string formatted for printing.
     *
     * @param d The DukeException that represents the error.
     * @return A string formatted for printing.
     */
    public String showError(QException d) {
        return print(d.getMessage());
    }

    /**
     * Gets a String saying bye.
     *
     * @return A String saying bye.
     */
    public String showBye() {
        return print("Finally. Good riddance.");
    }

    /**
     * Gets a String showing help.
     *
     * @return A String showing help.
     */
    public String showHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append(print("Good. You really needed this:"));
        sb.append(print("help - This command. You couldn't figure that out?"));
        sb.append(print("list - Lists all your existing tasks."));
        sb.append(print("find <description> - Finds a task which contains the description."));
        sb.append(print(""));
        sb.append(print("todo <description> - Creates a to-do item with a description."));
        sb.append(print("event <description> /at <date and time> -"
                + " Creates an event with a description at a certain time."));
        sb.append(print("deadline <description> /by <date and time> -"
                + " Creates a deadline with a description to be completed by a certain time."));
        sb.append(print("Examples of <date and time>: tomorrow morning, next Mon night, two Tuesdays from now."));
        sb.append(print(""));
        sb.append(print("delete <index> - Deletes the task of that index."));
        sb.append(print("done <index> - Completes the task of that index."));
        sb.append(print(""));
        sb.append(print("archive - Archives all tasks."));
        sb.append(print("view - View all archived tasks."));
        sb.append(print(""));
        sb.append(print("bye - Exits the program."));
        return sb.toString();
    }

    /**
     * Gets a String showing that there are no tasks.
     *
     * @return A String showing that there are no tasks.
     */
    public String showNoTasks() {
        return print("This is strange, but you have no tasks in your list.");
    }

    /**
     * Gets a String showing that no tasks are found.
     *
     * @return A String showing that no tasks are found.
     */
    public String showNoFindTasks() {
        return print("I don't see any tasks with that keyword. It's not my fault.");
    }

    /**
     * Gets a String showing the list of Tasks.
     *
     * @param tasks A list of Tasks.
     * @return A String showing the list of Tasks.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(print("Fine. I'll tell you the tasks in your list:"));
        for (String s : tasks.toUiStringList()) {
            sb.append(print(s));
        }
        return sb.toString();
    }

    /**
     * Gets a String showing that a task is done.
     *
     * @param task The task that has been marked as done.
     * @return A String showing that a task is done.
     */
    public String showDoneTask(Task task) {
        return print("Took you long enough. It's done:") + print(task.toString());
    }

    /**
     * Gets a String showing that a Task is deleted.
     *
     * @param task The task that was deleted.
     * @param size The size of the remaining Task list.
     * @return A String showing that a Task is deleted.
     */
    public String showDeleteTask(Task task, int size) {
        return print("Okay. Got rid of it.") + print(task.toString())
                + print("You have " + size + " tasks left.");
    }

    /**
     * Gets a String showing that a Task is added.
     *
     * @param task The task that was added.
     * @param size The size of the remaining Task list.
     * @return A String showing that a Task is added.
     */
    public String showAddTask(Task task, int size) {
        return print("Added this:") + print(task.toString())
                + print(size + " tasks left. Finish them quickly!");
    }

    /**
     * Gets a String showing the found tasks.
     *
     * @param tasks The found tasks.
     * @return A String showing the found tasks.
     */
    public String showFindTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(print("I guess I'll give you the matching tasks..."));
        for (String s : tasks.toUiStringList()) {
            sb.append(print(s));
        }
        return sb.toString();
    }

    /**
     * Gets a String showing the archived tasks.
     *
     * @param tasks The archived tasks.
     * @return A String showing the archived tasks.
     */
    public String showArchived(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(print("What you've archived is here:"));
        for (String s : tasks.toUiStringList()) {
            sb.append(print(s));
        }
        return sb.toString();
    }
}
