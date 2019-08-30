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
     */
    public void printWelcome() {
        prettyPrint(welcomeStr);
    }

    /**
     * Print exit string.
     */
    public void printExit() {
        prettyPrint(endStr);
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
     */
    public void showTaskAddition(Task task, TaskList tl) {
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n");
        sb.append("       " + task + "\n");
        sb.append(String.format("     Now you have %d tasks in the list.", tl.getTasks().size()));
        prettyPrint(sb.toString());
    }

    /**
     * Display a deletion of task.
     * @param task Task deleted.
     * @param tl TaskList deleted from.
     */
    public void showTaskDeletion(Task task, TaskList tl) {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:\n");
        sb.append(String.format("     %s\n", task.toString()));
        sb.append(String.format("     Now you have %d tasks in the list.", tl.getTasks().size()));
        prettyPrint(sb.toString());
    }

    /**
     * Display marking task as done.
     * @param task Task marked done.
     */
    public void showTaskMarkedDone(Task task) {
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
        sb.append(String.format("     %s", task.toString()));
        prettyPrint(sb.toString());
    }

    /**
     * List the tasks in a TaskList.
     * @param tl TaskList to list tasks from.
     */
    public void showTaskList(TaskList tl) {
        prettyPrint(tl.listTasks());
    }

    /**
     * Display an error.
     * @param e Error to be displayed.
     */
    public void printError(DukeException e) {
        prettyPrint(String.format("☹ OOPS!!! %s", e.getMessage()));
    }

    /**
     * Pretty print a string.
     * @param str String to be printed.
     */
    private static void prettyPrint(String str) {
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + str);
        System.out.println("    --------------------------------------------------");
    }
}
