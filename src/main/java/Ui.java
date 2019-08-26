import java.util.Scanner;

public class Ui {
    Scanner myScanner;

    public Ui() {
        myScanner = new Scanner(System.in);
    }

    public void showLoadingError() {

    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        return myScanner.nextLine();
    }
}
