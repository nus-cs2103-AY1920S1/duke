import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui () {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showText(String text) {
        System.out.println(text);
    }

    public void showError(DukeException error) {
        System.out.println(error.getMessage());
    }

    public void showLoadingError(DukeException   error) {
        System.out.println(error.getMessage());
    }

    public void showExitMessage() {
        System.out.println("Bye! Hope to see you again soon.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
