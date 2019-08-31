import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    public static final String TAB = "    ";
    public static final String DIVIDER = "____________________________________________________________";
    public Scanner sc;

    /**
     * Assigns a scanner for the Ui to get user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints a welcome message to the user.
     */
    public void showWelcome() {
        show(DIVIDER);
        show("Hello! I'm Duke");
        show("What can I do for you?");
        show(DIVIDER);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void showBye() {
        show("Bye. Hope to see you again soon!");
        show(DIVIDER);
    }

    public void showLine() {
        show(DIVIDER);
    }

    public void show(String message) {
        System.out.println(TAB + message);
    }

    /**
     * Retrieves error message if file cannot be loaded.
     *
     * @param e When file duke.txt cannot be found.
     */
    public void showLoadingError(FileNotFoundException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Reads user input from the scanner.
     * @return User input trimmed for leading and trailing whitespace.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }
}