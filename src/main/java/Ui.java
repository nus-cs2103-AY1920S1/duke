import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    /**
     * Shows welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Welcome message
        System.out.println("\t____________________________________________________________");
        System.out.println("\n\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");
    }

    /**
     * Prints the top border.
     */
    public void showTopBorder() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the bottom border.
     */
    public void showBottomBorder() {
        System.out.println("\t____________________________________________________________\n");
    }

    /**
     * Prints the list of tasks supplied.
     */
    public void printTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\n\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Prints a loading error.
     */
    public void showLoadingError() {
        showTopBorder();
        System.out.println("\n\tSorry! There was an error loading the files from the system.");
        showBottomBorder();
    }

    /**
     * Reads the input from user.
     * 
     * @return String representing the full command read
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }
}