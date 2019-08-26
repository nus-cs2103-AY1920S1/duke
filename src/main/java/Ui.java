import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public void showLoadingError() {
        System.out.println("File cannot be loaded.");
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public void showLine() {
        System.out.println("________________________________");
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public void print(String msg) {
        System.out.println(msg);
    }

    public void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            Task t = list.get(i-1);
            System.out.println( i + "." + t);
        }
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
