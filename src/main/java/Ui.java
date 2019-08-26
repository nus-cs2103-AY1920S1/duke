import java.util.Scanner;

public class Ui {

    public Ui() {}

    /**
     * Prints out reply message to the user.
     *
     * @param message String containing reply message.
     */
    public void show(String message) {
        showLine();
        System.out.println("\t" + message);
        showLine();
    }

    private void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints out reply message for adding a task to the user.
     *
     * @param task Task to print out reply for.
     */
    public void replyAddTask(Task task, int taskCount) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Got it mate. I've added this task:\n\t" + task + "\n\t");
        messageBuilder.append("Now you have " + taskCount + " tasks in the list mate.");
        show(messageBuilder.toString());
    }

    public void replyDoneTask(Task task) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Nice one mate! I've marked this task as done:\n\t");
        messageBuilder.append("  " + task);
        show(messageBuilder.toString());
    }

    public void replyDeleteTask(Task task, int taskCount) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Noted mate! I've removed this task:\n\t")
                .append("  " + task + "\n\t")
                .append("Now you have " + taskCount + " tasks in the list mate.");
        show(messageBuilder.toString());
    }

    /** Displays list of tasks */
    public void listTasks(TaskList tasks) {
        StringBuilder messageBuilder = new StringBuilder();

        if (tasks.size() > 0) {
            messageBuilder.append("Here are the tasks in your list:\n\t");
            for (int i = 0; i < tasks.size(); i++) {
                messageBuilder.append((i+1) + ". " + tasks.get(i) + "\n\t");
            }
            messageBuilder.setLength(messageBuilder.length() - 2); // strip trailing \n\t
        } else {
            messageBuilder.append("No tasks in your list. Add some tasks and get to work mate!");
        }

        show(messageBuilder.toString());
    }

    public void showLoadingError() {
        show("Oops! Unable to load tasks from hard disk, starting with an empty list.");
    }

    public void showWelcome() {
        String logo = " ____        _        \n\t"
                    + "|  _ \\ _   _| | _____ \n\t"
                    + "| | | | | | | |/ / _ \\\n\t"
                    + "| |_| | |_| |   <  __/\n\t"
                    + "|____/ \\__,_|_|\\_\\___|\n\t";
        String greeting = logo + "G'day mate! I'm Duke.\n\tWhatcha need help with?";
        show(greeting);
    }

    public void showBye() {
        String bye = "Bye mate. Catch you later!";
        show(bye); //say goodbye
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(String message) {
        show(message);
    }
}
