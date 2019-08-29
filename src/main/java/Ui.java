import java.util.Scanner;

/**
 * A class to deal with interactions with the user
 */

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Show welcome message
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Show initial list message
     */
    public void showInitialListMessage(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("You do not have any stored tasks");
        } else {
            System.out.println("This is your current list of tasks");
            for (int i = 0; i < tasks.size(); i++) {
                int currentItemNumber = i + 1;
                Task currentTask = tasks.get(i);
                System.out.println(currentItemNumber + "." + currentTask);
            }
        }
    }

    /**
     * Show good bye message
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Show error message
     */
    public void showErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Get user input
     *
     * @return String that user inputted
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
