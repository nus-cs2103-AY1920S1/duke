import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Greets user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    /**
     * Prints separator line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the error message when loading data from hard disk.
     * 
     * @param ex The exception.
     */
    public void showLoadingError(DukeException ex) {
        showLine();
        showErrorMessage(ex);
        showLine();
    }

    /**
     * Prints error message.
     * 
     * @param ex The exception.
     */
    public void showErrorMessage(DukeException ex) {
        System.out.println(ex);
    }

    /**
     * Prints normal message.
     * 
     * @param message The message.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Reads user command.
     * 
     * @return user command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
