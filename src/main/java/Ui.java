import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    private String formatMessage(String message) {
        return "     " + message + "\n";
    }

    String readCommand() {
        return this.scanner.next();
    }

    String readDetails() {
        return this.scanner.nextLine().trim();
    }

    void echo(String... messages) {
        String border = "    ____________________________________________________________\n";
        StringBuilder toEcho = new StringBuilder(border);
        for (String message : messages) {
            toEcho.append(formatMessage(message));
        }
        toEcho.append(border);
        System.out.println(toEcho);
    }

    void greet() {
        echo("Hello! I'm Jermi", "What can I do for you?");
    }

    void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    void runFail() {
        echo("Client has failed to run.", "Please resolve the issue above before running again.");
    }
}