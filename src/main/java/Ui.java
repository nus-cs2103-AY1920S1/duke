import java.util.Scanner;

public class Ui {

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        System.out.println("Unable to access existing tasklist; initialising new tasklist instead");
    }

    public void print(String msg) {
        System.out.println(msg);
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
