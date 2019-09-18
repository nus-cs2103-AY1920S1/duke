import java.util.Scanner;

/** Class to handle UI for the application. */
class UI {

    // scanner to take user input
    private Scanner sc;
    private static final String WELCOME_STR = "Hello! I'm Duke :)\n     What can I do for you?";
    private static final String END_STR = "Bye. Hope to see you again soon!";

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
        return prettyPrint(WELCOME_STR);
    }

    /**
     * Print exit string.
     * @return formatted string.
     */
    public String printExit() {
        return prettyPrint(END_STR);
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
     * Display task which is being updated.
     * @param task Task being updated.
     * @return formatted string.
     */
    public String showTaskBeingUpdated(Task task) {
        StringBuilder sb = new StringBuilder("Okay! Updating this task:\n");
        sb.append(String.format("     %s\n\n", task.toString()));
        sb.append(String.format("     %s\n", "Please use one of these options to update:"));
        sb.append(String.format("     %s\n", "1. name <new_name>"));
        sb.append(String.format("     %s\n", "2. date <new_date>"));
        sb.append(String.format("     %s\n", "3. both <new_name> <new_date>"));
        return prettyPrint(sb.toString());
    }

    /**
     * Display task which just got updated.
     * @param task Task updated.
     * @return formatted string.
     */
    public String showTaskIsUpdated(Task task) {
        StringBuilder sb = new StringBuilder("Okay! Here's the updated task:\n");
        sb.append(String.format("     %s", task.toString()));
        return prettyPrint(sb.toString());
    }

    /**
     * Display an error.
     * @param e Error to be displayed.
     * @return formatted error.
     */
    public String printError(DukeException e) {
        return prettyPrint(String.format("%s OOPS!!! %s", Unicode.SAD_FACE, e.getMessage()));
    }

    /**
     * Pretty print a string.
     * @param str String to be printed.
     * @return Formatted string.
     */
    private static String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("    --------------------------------------------\n");
        sb.append("     " + str + "\n");
        sb.append("    --------------------------------------------\n");
        return sb.toString();
    }
}
