import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }


    /**
     * This method is used to print loading error to user.
     */
    public void showLoadingError() {
        System.out.println("File cannot be loaded.");
    }

    /**
     * This method is used to print welcome message to user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");
    }

    public void showLine() {
        System.out.println("________________________________");
    }

    /**
     * This method is used to read user command.
     *
     * @return String This returns user command as a string.
     */
    public String readCommand() {

        return scanner.nextLine();
    }

    public void print(String msg) {
        System.out.println(msg);
    }

    /**
     * This method is used to print current items in the list to user.
     *
     * @param list the current task list
     * @return Nothing
     */
    public void printList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= list.size(); i++) {
            Task t = list.get(i - 1);
            System.out.println(i + "." + t);
        }
    }

    /**
     * This method is used to print welcome message to user.
     *
     * @return Nothing
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
