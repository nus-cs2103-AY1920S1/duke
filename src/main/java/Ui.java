import java.util.Scanner;

public class Ui {

    public void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public String readInput(Scanner sc) {
        return sc.nextLine();
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("File not found.");
    }
}
