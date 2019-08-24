import java.util.Scanner;

public class Ui {
    private static String prefix = "☹ OOPS!!!";

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Handles the actual printing of error messages to the system output.
     * All printing of error messages should be routed through this method.
     * @param errorMessage Error message to be printed
     */
    private void showError(String errorMessage) {
        System.out.println(prefix + " " + errorMessage);
    }

    public void showError(DukeException e) {
        showError(e.getMessage());
    }

    /**
     * Handles the actual printing of messages to the system output.
     * All printing of messages should be routed through this method.
     * @param message Message to be printed
     */
    private void showMessage(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println();
    }

    public void showLoadingError() {
        showError("Could not load your tasks.");
    }

    public void showSavingError() {
        showError("Could not save your tasks.");
    }

    public void showWelcome() {
        showMessage("Hello! I'm Duke");
        showMessage("What can I do for you?");
    }

    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
