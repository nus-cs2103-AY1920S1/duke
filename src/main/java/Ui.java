import java.util.Scanner;

/**
 * Takes care of the Ui.
 */
public class Ui {
    private Scanner scanner;
    private String currentMessage;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        currentMessage = "";
        scanner = new Scanner(System.in);
    }

    /**
     * Method to read the next line.
     *
     * @return A string representing the next line
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Method to print out a line.
     */
    public void showLine() {
        println("____________________________________________________________");
    }

    /**
     * Method to display error.
     *
     * @param message which is the error message
     */
    public void showError(String message) {
        System.out.println(message);
        currentMessage += message + "\n";

    }

    /**
     * Method to display the welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static String getWelcomeMessage() {
        return "Hello! I'm Duke\n" + "What can I do for you?\n" + "Enter help for commands";
    }

    /**
     * Method to print a message.
     *
     * @param message to be printed
     */
    public void println(String message) {
        currentMessage += message + "\n";
        System.out.println(message);
    }

    /**
     * Method to print a task.
     *
     * @param task to be printed
     */
    public void println(Task task) {
        System.out.println(task);
        currentMessage += task.toString() + "\n";
    }

    /**
     * Method to get the current message to be printed
     *
     * @return A string representing the current message
     */
    public String getMessage() {
        String temp = currentMessage;
        currentMessage = "";
        return temp;
    }

}