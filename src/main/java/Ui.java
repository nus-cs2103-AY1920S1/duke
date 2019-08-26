import java.util.Scanner;

/**
 * Takes in the user input into a Duke object and displays output to the user.
 */
public class Ui {
    protected String line = "    ____________________________________________________________";
    protected String welcomeMessage = "     Hello! I'm Duke\n     What can I do for you?";
    protected String byeMessage = "     Bye. Hope to see you again soon!";
    Scanner scanner;

    /**
     * Creates an Ui object to take input user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next input from the user.
     *
     * @return the String command input by the user.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Displays the welcome message when a Duke object is started.
     */
    public void showWelcomeMessage() {
        System.out.println(line);
        System.out.println(this.welcomeMessage);
        System.out.println(line);
    }

    /**
     * Displays the list of tasks in the TaskList object argument.
     *
     * @param tasks TaskList object with tasks to be displayed to the user in a list.
     */
    public void showListMessage(TaskList tasks) {
        System.out.println(line);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.taskListSize(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(line);
    }

    /**
     * Displays te exception message of the argument exception to the user.
     *
     * @param e Exception whose message needs to be displayed.
     */
    public void showExceptionError(Exception e) {
        System.out.println(line);
        System.out.println("     " + e.getMessage());
        System.out.println(line);
    }

    /**
     * Displays message when a task is added to the TaskList object arugment.
     *
     * @param task Task object to be added to TaskList object.
     * @param taskListSize Number of Task objects in TaskList object after the Task object is added.
     */
    public void showAddTaskMessage(Task task, int taskListSize) {
        System.out.println(line);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskListSize
                + (taskListSize > 1 ? " tasks in the list." : " task in the list."));
        System.out.println(line);
    }

    /**
     * Displays message when a Task object is marked as done.
     *
     * @param task Task object that is marked as done.
     */
    public void showMarkTaskAsDoneMessage(Task task) {
        System.out.println(line);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task);
        System.out.println(line);
    }

    /**
     * Displays message when a Task object is deleted.
     *
     * @param taskListSize Number of Task objects in TaskList object after the Task object is deleted.
     */
    public void showDeleteTaskMessage(Task task, int taskListSize) {
        System.out.println(line);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + taskListSize
                + (taskListSize > 1 ? " tasks in the list." : " task in the list."));
        System.out.println(line);
    }

    /**
     * Dsiplays exit message when a Duke object stops running.
     */
    public void showByeMessage() {
        System.out.println(line);
        System.out.println(this.byeMessage);
        System.out.println(line);
    }

    /**
     * Displays the list of tasks in the TaskList object argument.
     * The tasks in the TaskList object argument contains the keyword entered by the user.
     *
     * @param tasks TaskList object with tasks to be displayed to the user in a list.
     */
    public void showFindTasksMessage(TaskList tasks) {
        System.out.println(line);
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.taskListSize(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.getTask(i));
        }
        System.out.println(line);
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getByeMessage() {
        return byeMessage;
    }

    public void setByeMessage(String byeMessage) {
        this.byeMessage = byeMessage;
    }
}
