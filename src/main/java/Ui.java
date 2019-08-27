import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {

    public Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");
    }

    public void showBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________\n");
    }

    public void showLoadingError(FileNotFoundException e) {
        System.out.println(e.getMessage());
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }
}