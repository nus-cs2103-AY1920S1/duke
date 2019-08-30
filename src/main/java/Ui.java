import java.util.Scanner;

public class Ui {
    private Parser inputParser = new Parser();
    Scanner scanner = new Scanner(System.in);
    boolean isExit = false;

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        this.showLine();
        this.printText("Hello! I'm Duke\n" +
                "What can I do for you?");
        this.showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }


    public void showLoadingError() {
        System.out.println("Unable to load");
    }

    public void closeUi() {
        this.isExit = true;
        this.scanner.close();
    }


    public void printText(String text) {

        System.out.println(text);

    }


    public void showError(String message) {
        System.out.println(message);
    }
}


