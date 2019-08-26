/**
 * This class deals with the interactions with the user. It contains methods to return responses to the user when
 * the user gives an input.
 */
package duke.managers;
import java.util.Scanner;

public class Ui {
    static Scanner sc = new Scanner(System.in);

    public Ui() {

    }

    public static void showLoadingError() {
        System.out.println("No tasks to load. A new TaskList will be created.");
    }

    public static void showWelcome() {
        System.out.println("Hello I'm Duke.");
        System.out.println("What can I do for you?");
    }

    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public static void showLine() {
        String border = "";
        for (int i = 0; i < 80; i++) {
            border += "-";
        }
        System.out.println(border);
    }

    /**
     * This method takes in the entire line of command for Duke to process.
     */
    public static String readCommand() {
        return sc.nextLine();
    }

    /**
     * This method prints the message for the user to read.
     * @param string containing the response to the user's input
     */
    public void printLine(String string) {
        System.out.println(string);
    }
}
