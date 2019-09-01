import java.util.Scanner;

/**
 * Reads user input and prints response as output.
 */
public class Ui {

    private static Scanner sc = new Scanner(System.in);

    /**
     * Constructor method for UI.
     */
    public Ui() {
    }

    /**
     * Displays an initial welcome message to the user.
     */
    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Displays an error message upon failure to open data file.
     */
    public static void showLoadingError() {
        System.out.println("Unable to open file. Initialising empty file list.");
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return A single line of user input.
     */
    public static String getNextLine() {
        String nextLine = sc.nextLine();
        return nextLine;
    }

    /**
     * Displays a message informing the user that a new task has been added to the task list.
     *
     * @param task Task that was added.
     * @param taskList List of existing tasks.
     * @return String of task added.
     */
    public static String outputTaskAdded(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n"
                + task.toString() + "\n"
                +("Now you have " + taskList.getSize() + " task in the list.");
    }

    /**
     * Displays a message informing the user that a task has been deleted.
     *
     * @param task Task that was deleted.
     * @param taskList List of existing tasks.
     * @return String of task removed.
     */
    public static String outputTaskRemoved(Task task, TaskList taskList) {
        return "Got it. I've removed added this task:\n"
                + task.toString() + "\n"
                +("Now you have " + taskList.getSize() + " task in the list.");
    }

    /**
     * Displays a message informing the user that a task has been marked as done.
     *
     * @param task Task that was marked as done.
     * @return String of task marked as done.
     */
    public static String outputTaskDone(Task task) {
        return "Nice! I've marked this task as done:"
                + "[" + task.getStatusIcon() + "] " + task.getDescription();
    }
}
