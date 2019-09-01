import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a user interface which interacts with the user.
 */
public class Ui {
    Scanner scanner = new Scanner(System.in);

    /**
     * Default constructor.
     */
    public Ui() {
    }

    /**
     * Returns String representation of user input.
     * @return String representation of user input
     */
    public String readCommand() {
        String command = "";
        if (scanner.hasNextLine()) {
            command = scanner.nextLine();
        }
        return command;
    }

    /**
     * Prints out the number of tasks in the taskList.
     * @param taskList
     */
    public void printNumberOfTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getTasks().size()
                + " tasks in the list.");
    }

    /**
     * Prints out the string representation of task t.
     * @param t Task to be printed out
     */
    public void printTask(Task t) {
        System.out.println(t.toString());
    }

    /**
     * Prints out the string representation of all the tasks in taskList
     * @param taskList
     */
    public void printTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        int number = 1;
        for (Task task : tasks) {
            String outputString = number + ". " + task.toString();
            System.out.println(outputString);
            number++;
        }
    }

    /**
     * Prints out the error message.
     * @param error
     */
    public void printErrorMessage(String error) {
        System.out.println("OOPS!!! " + error );
    }

    /**
     * Prints out the pre-message for the add command.
     */
    public void printAddedMessage() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints out the pre-message for the delete command.
     */
    public void printDeletedMessage() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Prints out the pre-message for the done command.
     */
    public void printDoneMessage() {
        System.out.println("Nice! I've marked this task as done: ");
    }

    /**
     * Prints out the pre-message for the list command.
     */
    public void printListMessage() {
        System.out.println("Here are the tasks in your list: ");
    }

    /**
     * Prints out greetings for Duke bot.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Prints out the message for the bye command.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the loading message error
     */
    public void showLoadingError() {
        System.out.println("Error, file not found");
    }

    /**
     * Prints out the message for the find command.
     */
    public void printFindMessage(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (Task t : tasks) {
            System.out.println(count + "." + t.toString());
            count++;
        }
    }
}
