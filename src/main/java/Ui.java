import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

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
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void showBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
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