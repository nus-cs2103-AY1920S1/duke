import java.util.Scanner;

/**
 * Handles the input and output of Duke.
 */
public class Ui {
    private Scanner input;

    /**
     * Creates a new Ui.
     */
    public Ui() {
        input = new Scanner(System.in);
    }

    /**
     * Reads the next line of the commands.
     * @return a String representing the next line of command.
     */
    public String readCommand() {
        String command = input.nextLine().trim(); //trim leading/trailing whitespace
        return command;
    }

    /**
     * Prints the welcome message of Duke.
     */
    public void showWelcome() {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);
    }

    /**
     * Inform users that the file cannot be loaded.
     */
    public void showLoadingError() {
        System.out.println("File cannot be loaded");
    }

    /**
     * Indicate that the task has been successfully added to the task list.
     * @param newTask new task to be added.
     * @param numTasks current number of tasks in the list.
     */
    public void showAddTask(Task newTask, int numTasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println("Now you have " + numTasks + " tasks in the list.");
    }

    /**
     * Print out all the tasks that are currently in the list.
     * @param tasks list of all tasks.
     */
    public void showList(TaskList tasks) {
        tasks.printAllTasks();
    }

    /**
     * Prints the farewell message.
     */
    public void showFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    /**
     * Prints the given error message.
     * @param e
     */
    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Indicates that the given task has been successfully deleted.
     * @param deletedTask deleted task
     * @param numTasks number of tasks left in the list
     */
    public void showDeletedTask(Task deletedTask, int numTasks) {
        System.out.println("Noted. I've removed this task:\n"
                + "  "
                + deletedTask
                + String.format("\nNow you have %d tasks in the list.", numTasks));
    }

    /**
     * Prints the list of Tasks that are relevant
     * @param filteredList of tasks related to the keyword
     */
    public void showSearchList(TaskList filteredList) {
        System.out.println("Here are the matching tasks in your list:");
        filteredList.printAllTasks();
    }

    /**
     * Error with accessing an item not on the list. Prompts for user to check again by calling list.
     */
    public void promptList() {
        System.out.println("OOPS!!! That task is not on the list, please check the list again by calling 'list'.");
    }

    /**
     * Incomplete done command. Prompts for the index of the task to be marked as done.
     */
    public void promptDoneCompletion() {
        System.out.println("Which task on the list have you completed? (Eg 'done 2')");
    }

    /**
     * Incorrect format for done command. Prompts for an integer index.
     */
    public void promptDoneFormat() {
        System.out.println("OOPS!!! Wrong format. Please key in a valid number (Eg 'done 2')");
    }

    /**
     * Incomplete delete command. Prompts for the index of the task to be deleted.
     */
    public void promptDeleteCompletion() {
        System.out.println("Which task on the list would you like to delete? (Eg. 'delete 2')");
    }

    /**
     * Incorrect format for delete command. Prompts for an integer index.
     */
    public void promptDeleteFormat() {
        System.out.println("OOPS!!! Wrong format. Please key in a valid number (Eg 'delete 2')");
    }

    public void promptFindKeyword() {
        System.out.println("Please key in a keyword to search for. (Eg 'find book')");
    }
}