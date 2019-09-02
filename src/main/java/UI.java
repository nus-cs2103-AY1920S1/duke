import java.util.Scanner;

/** Class to handle UI for the application. */
class UI {

    // scanner to take user input
    private Scanner sc;
    private static final String welcomeStr = "Hello! I'm Duke :)\n     What can I do for you?";
    private static final String endStr = "Bye. Hope to see you again soon!";

    /**
     * Constructor for the object.
     */
    public UI() {
        sc = new Scanner(System.in);
    }

    /**
     * Print welcome string.
     * @return formatted string.
     */
    public String printWelcome() {
        return prettyPrint(welcomeStr);
    }

    /**
     * Print exit string.
     * @return formatted string.
     */
    public String printExit() {
        return prettyPrint(endStr);
    }

    /**
     * Take input from user.
     * @return User input.
     */
    public String takeInput() {
        if (this.sc.hasNextLine()) {
            return sc.nextLine();
        }

        return null;
    }

    /**
     * Method to display task addition.
     * @param task Task added.
     * @param tl TaskList of tasks.
     * @return formatted string.
     */
    public String showTaskAddition(Task task, TaskList tl) {
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n");
        sb.append("       " + task + "\n");
        sb.append(String.format("     Now you have %d tasks in the list.", tl.getTasks().size()));
        return prettyPrint(sb.toString());
    }

    /**
     * Display a deletion of task.
     * @param task Task deleted.
     * @param tl TaskList deleted from.
     * @return formatted string.
     */
    public String showTaskDeletion(Task task, TaskList tl) {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:\n");
        sb.append(String.format("     %s\n", task.toString()));
        sb.append(String.format("     Now you have %d tasks in the list.", tl.getTasks().size()));
        return prettyPrint(sb.toString());
    }

    /**
     * Display marking task as done.
     * @param task Task marked done.
     * @return formatted string.
     */
    public String showTaskMarkedDone(Task task) {
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
        sb.append(String.format("     %s", task.toString()));
        return prettyPrint(sb.toString());
    }

    /**
     * List the tasks in a TaskList.
     * @param tl TaskList to list tasks from.
     * @return formatted list of tasks.
     */
    public String showTaskList(TaskList tl) {
        return prettyPrint(tl.listTasks());
    }

    /**
     * Display an error.
     * @param e Error to be displayed.
     * @return formatted error.
     */
    public String printError(DukeException e) {
        return prettyPrint(String.format("☹ OOPS!!! %s", e.getMessage()));
    }

    /**
     * Pretty print a string.
     * @param str String to be printed.
     * @return Formatted string.
     */
    private static String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("    --------------------------------------------------\n");
        sb.append("     " + str + "\n");
        sb.append("    --------------------------------------------------\n");
        return sb.toString();
    }
}
