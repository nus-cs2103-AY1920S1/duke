import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError(DukeException ex) {
        showLine();
        showErrorMessage(ex);
        showLine();
    }

    public void showErrorMessage(DukeException ex) {
        System.out.println(ex);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
