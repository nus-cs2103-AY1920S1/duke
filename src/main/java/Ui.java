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

    public String showWelcome() {
        return ("Hello from\n" + LOGO) + "\n" +
        ("Hello! I'm Duke\nWhat can I do for you?") + "\n" +
                ("Type 'help' for a list of commands");
    }

    /**
     * Reads the command from the user.
     * @return a command in String form
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Retrieving various messages
     */
    public String exitMessage() {
        return "Bye. Hope to see you again soon!";
    }

    public String doneMessage(Task task) {
        return "Nice! I've marked this task as done\n" + task;
    }

    public String deleteMessage(TaskList tasks, Task task) {
        tasks.delete(task);
        return ("Noted. I've removed this task:\n\t" + task + "\n" +
        "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public String addMessage(TaskList tasks, Task task) {
        return ("Got it. I've added this task: \n\t"
                + task + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
    public String listMessage(TaskList tasks) {
        return ("Here are the tasks in your list:\n" +
        tasks.showList()); // this prints the list of tasks
    }

    public String helpMessage(Storage storage) {
        return storage.getHelpInfo();
    }

    public String showLine() {
        return ("___________________________________________________");
    }

    public String getStatistics(Statistics s) {
        return s.toString();
    }
    public String getFunFact() {
        return "Fun fact! Did you know that...?";
    }
    /**
     * A method to close the scanner
     */

    public void closeScanner() {
        this.sc.close();
    }

    public String showLoadingError() {
        return ("Error while loading");
    }
    public String showError(String s) {
        return (s);
    }
}
