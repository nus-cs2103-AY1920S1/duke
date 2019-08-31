import java.util.Scanner;

/**
 * Takes in the user input into a Duke object and displays output to the user.
 */
public class Ui {
    protected String line = "    ____________________________________________________________" + "\n";
    protected String welcomeMessage = "     Hello! I'm Duke\n     What can I do for you?\n";
    protected String byeMessage = "     Bye. Hope to see you again soon!\n";
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
     * Returns the welcome message when a Duke object is started.
     *
     * @return String welcome message when Duke is started.
     */
    public String showWelcomeMessage() {
        return line + welcomeMessage + line;
    }

    /**
     * Returns the list of tasks in the TaskList object argument.
     *
     * @param tasks TaskList object with tasks to be displayed to the user in a list.
     * @return String list of tasks in TaskList.
     */
    public String showListMessage(TaskList tasks) {
        String list = line + "     Here are the tasks in your list:" + "\n";
        for (int i = 0; i < tasks.taskListSize(); i++) {
            list = list + "     " + (i + 1) + "." + tasks.getTask(i) + "\n";
        }
        list = list + line;

        return list;
    }

    /**
     * Returns the exception message of the argument exception to the user.
     *
     * @param e Exception whose message needs to be displayed.
     * @return String exception message of the argument exception.
     */
    public String showExceptionError(Exception e) {
        return line + "     " + e.getMessage() + line;
    }

    /**
     * Returns message when a task is added to the TaskList object arugment.
     *
     * @param task Task object to be added to TaskList object.
     * @param taskListSize Number of Task objects in TaskList object after the Task object is added.
     * @return String message when a task is added to the TaskList.
     */
    public String showAddTaskMessage(Task task, int taskListSize) {
        String output = line + "     Got it. I've added this task: \n" + "       " + task.toString() + "\n"
                + "     Now you have " + taskListSize + " task(s) in the list.\n" + line;

        return output;
    }

    /**
     * Returns message when a Task object is marked as done.
     *
     * @param task Task object that is marked as done.
     * @return String message when a Task object is marked as done.
     */
    public String showMarkTaskAsDoneMessage(Task task) {
        return line + "     Nice! I've marked this task as done:\n       " + task.toString() + "\n" + line;
    }

    /**
     * Returns String message when a Task object is deleted.
     *
     * @param taskListSize Number of Task objects in TaskList object after the Task object is deleted.
     * @return String message when a Task object is deleted.
     */
    public String showDeleteTaskMessage(Task task, int taskListSize) {
        return line + "     Noted. I've removed this task:\n       " + task.toString()
                + "\n     Now you have " + taskListSize + " task(s) in the list.\n" + line;
    }

    /**
     * Returns exit message when a Duke object stops running.
     *
     * @return String exit message when a Duke object stops running.
     */
    public String showByeMessage() {
        return line + byeMessage + line;
    }

    /**
     * Returns the list of tasks in the TaskList object argument.
     * The tasks in the TaskList object argument contains the keyword entered by the user.
     *
     * @param tasks TaskList object with tasks to be displayed to the user in a list.
     * @return String list of tasks in the TaskList object argument.
     */
    public String showFindTasksMessage(TaskList tasks) {
        String list = line + "     Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.taskListSize(); i++) {
            list = list + "     " + (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        list = list + line;
        return list;
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
