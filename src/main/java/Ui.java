import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with interactions with the user
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Greets the user
     */
    public void greeting () {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Bids farewell to the user
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Lists all the Tasks to the user
     */
    public void list () { System.out.println("Here are the tasks in your list:"); }

    /**
     * Notifies user that a Task was added to the tasks list
     * @param list
     */
    public void add (ArrayList<Task> list) {

        System.out.println("Got it. I've added this task:");

        System.out.println(list.get(list.size() - 1));

        System.out.printf("Now you have %d tasks in the list.\n", list.size());

    }


    /**
     * Notifies the user that a task was deleted from the task list
     * @param task list
     * @param task Task that was deleted
     */
    public void delete(ArrayList<Task> list, Task task) {

        System.out.println(task);

        System.out.printf("Now you have %d tasks in the list.\n", list.size());
    }

    /**
     * Notifies user that a task was completed (done status changed to true)
     * @param task Task that was completed
     */
    public void done(Task task) {

        System.out.println("Nice! I've marked this task as done:");

        System.out.println(task);
    }

    /**
     * Shows an error to the user
     * @param message The error message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads input from the user
     * @return The input read from the user
     */
    public String readCommand() {

        return scanner.nextLine();
    }

    /**
     * Aborts the application
     */
    public void abort() {
        System.out.println("SORRY SOMETHING WENT SERIOUSLY WRONG! \nGoodbye!");
    }
}
