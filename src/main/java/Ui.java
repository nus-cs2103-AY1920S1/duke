import java.util.Scanner;

/**
 * Deals with user input and system output.
 */
public class Ui {
    protected Scanner sc;
    protected String nextCommand;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message on screen.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    /**
     * Takes in user input.
     * @return String pertaining to user input
     */
    public String readCommand() {
        this.nextCommand = sc.nextLine();

        return nextCommand;
    }

    /**
     * Returns to the next line.
     */
    public void showLine() {
        System.out.println("\n");
    }

    /**
     * Prints a message.
     * @param message String to be displayed on screen
     */
    public void showLine(String message) {
        System.out.println(message);
    }

    /**
     * Prints error message.
     * @param errorMessage specifies what kind of error and what user actions are needed
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints error if data cannot be read from file.
     */
    public void showLoadingError() {
        System.out.println("There was an error loading from the file! Please try again.");
    }
}

