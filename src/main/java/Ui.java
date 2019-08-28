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

    public void showLine() {
        System.out.print(oneLine);
    }

    /**
     * showError(String message).
     */
    public void showError(String message) {
        System.out.println(" " + frontSpace + message);
    }

    /**
     * showWelcome().
     */
    public void showWelcome() {
        String greet = "     Hello! I'm Duke\n"
                + "     What can I do for you?\n";
        greet = oneLine + greet + oneLine;
        System.out.println(greet);
    }

    /**
     * showLoadingError().
     */
    public void showLoadingError() {
        showLine();
        System.out.println(frontSpace + " duke.txt file has problem!");
        showLine();
    }
}