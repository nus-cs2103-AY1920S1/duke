import java.util.Scanner;

/**
 * Manages everything related to the UI.
 */
public class Ui {
    private Scanner scanner;
    private static String oneLine = "    ____________________________________________________________\n";
    public static String frontSpace = "    ";

    /**
     * Ui.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * used to readComand.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    public String showLine() {
        return oneLine;
    }

    /**
     * showError(String message).
     */
    public void showError(String message) {
        printOutput(frontSpace + message);
    }


    public static String getWelcomeMessage() {
        return "    Hello! I'm Duke.\n"
                + "    What can I do for you?";
    }

    public static void printOutput(Object output) {
        System.out.println(frontSpace + output);
    }

    /**
     * showLoadingError().
     */
    public void showLoadingError() {
        showLine();
        Ui.printOutput(" duke.txt file has problem!");
        showLine();
    }
}