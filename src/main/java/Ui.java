import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
    }


//    public void showLoadingError() {
//
//    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("    " + message);
    }
}
