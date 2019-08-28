import java.util.Scanner;

public class Ui {
    private String displayText;
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }


    /**
     * Print a greeting message to welcome the user
     */
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);
    }

    /**
     * Set the displayText variable to print a goodbye message
     */
    public void showBye() {
        displayText = "Bye. Hope to see you again soon!";
    }

    /**
     * Print an error message when the tasks could not be loaded from the txt file
     */
    public void showLoadingError() {
        System.out.println("\\u2639 OOPS!!! I'm sorry, but I could not load your saved task list ");
    }

    /**
     * Read the user input
     * @return user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Print any error message
     * @param message
     */
    public void showError(String message) {
        StringBuilder sb = new StringBuilder("\u2639 OOPS!!! I'm sorry, but ");
        sb.append(message);
        System.out.println(sb.toString());
    }

    /**
     * Print the saved text and reset the variable
     */
    public void showLine() {
        System.out.println(displayText);
        displayText = "";
    }

    /**
     * Set the displayText message that is to be printed when showLine() is called
     * @param str
     */
    public void setText(String str) {
        displayText = str;
    }

    /**
     * Set the displayText message when a task is saved
     * @param task
     * @param size
     */
    public void showTaskSaved(Task task, int size) {
        displayText = "Got it. I've added this task:\n\t" + task + "\nNow you have " + size + " tasks in the list.";
    }
}
