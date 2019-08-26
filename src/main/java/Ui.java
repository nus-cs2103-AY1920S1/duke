import java.util.Scanner;

public class Ui {
    protected Scanner sc;
    protected String nextCommand;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public String readCommand() {
        this.nextCommand = sc.nextLine();

        return nextCommand;
    }

    public void showLine() {
        System.out.println("\n");
    }

    public void showLine(String message) {
        System.out.println(message);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        System.out.println("There was an error loading from the file! Please try again.");
    }
}

