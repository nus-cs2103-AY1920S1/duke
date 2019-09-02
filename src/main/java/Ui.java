import java.util.ArrayList;
import java.util.Scanner;


/**
 * TextUi of the Application
 */

public class Ui {

    private Scanner sc = new Scanner(System.in);

    /** Duke Logo to display upon startup. */
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // empty constructor
    public Ui() {

    }

    /**
     * showWelcome greets the user
     */

    public void showWelcome() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Reads the command from the user.
     * @return a command in String form
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Printing various messages
     */
    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printRemovedMessage(Task task) {
        System.out.println("Nice! I've marked this task as done\n" + task);
    }

    public void printDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done\n" + task);
    }

    public void printDeleteMessage(TaskList tasks, Task task) {
        System.out.println("Noted. I've removed this task:\n\t" + task);
        tasks.delete(task);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void printAddMessage(TaskList tasks, Task task) {
        System.out.println("Got it. I've added this task: \n\t"
                + task + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
    public void printListMessage(TaskList tasks) {
        System.out.println("Here are the tasks in your list:\n");
        tasks.showList(); // this prints the list of tasks
    }

    public void showLine() {
        System.out.println("___________________________________________________");
    }

    /**
     * A method to close the scanner
     */

    public void closeScanner() {
        this.sc.close();
    }

    public void showLoadingError() {
        System.out.println("Error while loading");
    }
    public void showError(String s) {
        System.out.println(s);
    }
}
