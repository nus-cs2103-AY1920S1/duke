import java.util.Scanner;

/**
 * Handles the input and output of Duke.
 */
public class Ui {

    /**
     * Prints the welcome message of Duke.
     */
    public String showWelcome() {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        return greetings;
    }

    /**
     * Inform users that the file cannot be loaded.
     */
    public String showLoadingError() {
        return ("File cannot be loaded");
    }

    /**
     * Indicate that the task has been successfully added to the task list.
     * @param newTask new task to be added.
     * @param numTasks current number of tasks in the list.
     */
    public String showAddTask(Task newTask, int numTasks) {
        String s1 = "Got it. I've added this task:";
        String s2 = "  " + newTask.toString();
        String s3 = "Now you have " + numTasks + " tasks in the list.";
        return s1 + "\n" + s2 + "\n" + s3;
    }

    /**
     * Print out all the tasks that are currently in the list.
     * @param tasks list of all tasks.
     */
    public String showList(TaskList tasks) {
        return tasks.printAllTasks();
    }

    /**
     * Prints the farewell message.
     */
    public String showFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        return farewell;
    }

    /**
     * Prints the given error message.
     * @param e error given
     */
    public String showErrorMessage(Exception e) {
        return (e.getMessage());
    }

    /**
     * Indicates that the given task has been successfully deleted.
     * @param deletedTask deleted task
     * @param numTasks number of tasks left in the list
     */
    public String showDeletedTask(Task deletedTask, int numTasks) {
        return ("Noted. I've removed this task:\n"
                + "  "
                + deletedTask
                + String.format("\nNow you have %d tasks in the list.", numTasks));
    }

    /**
     * Prints the list of Tasks that are relevant.
     * @param filteredList of tasks related to the keyword.
     */
    public String showSearchList(TaskList filteredList) {
        String header = ("Here are the matching tasks in your list:");
        String list = filteredList.printAllTasks();
        return header + "\n" + list;
    }

    /**
     * Error with accessing an item not on the list. Prompts for user to check again by calling list.
     */
    public String promptList() {
        return "OOPS!!! That task is not on the list, please check the list again by calling 'list'.";
    }

    /**
     * Incomplete done command. Prompts for the index of the task to be marked as done.
     */
    public String promptDoneCompletion() {
        return "Which task on the list have you completed? (Eg 'done 2')";
    }

    /**
     * Incorrect format for done command. Prompts for an integer index.
     */
    public String promptDoneFormat() {
        return "OOPS!!! Wrong format. Please key in a valid number (Eg 'done 2')";
    }

    /**
     * Incomplete delete command. Prompts for the index of the task to be deleted.
     */
    public String promptDeleteCompletion() {
        return "Which task on the list would you like to delete? (Eg. 'delete 2')";
    }

    /**
     * Incorrect format for delete command. Prompts for an integer index.
     */
    public String promptDeleteFormat() {
        return "OOPS!!! Wrong format. Please key in a valid number (Eg 'delete 2')";
    }

    public String promptFindKeyword() {
        return "Please key in a keyword to search for. (Eg 'find book')";
    }
}