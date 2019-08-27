package duke.ui;

import java.util.Scanner;

public class Ui {

    private static final String INDENT = "    ";
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println(INDENT + " " + message);
    }

    public void showError(String error) {
        showLine();
        showMessage("☹ OOPS!!! " + error);
        showLine();
    }

    public void showWelcome() {
        showLine();
        showMessage("Hello! I'm Duke");
        showMessage("What can I do for you?");
        showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
