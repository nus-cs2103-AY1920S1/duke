package duke;

import task.Task;

import java.util.Scanner;

/**
 * Deals with user interaction for Duke.
 */
public class Ui {
    public static final String LINE = "    ____________________________________________________________\n";

    /**
     * Greets the user with default text.
     */
    public void greet() {
        showLine();
        String greeting = "     Hello! I'm Duke\n"
                        + "     What can I do for you?\n";
        showLine();

        System.out.println(greeting);
    }

    /**
     * Shows a horizontal line.
     */
    public void showLine() {
        // do nothing
        // System.out.print(LINE);
    }

    /**
     * Shows the message preceding a task that has just been added.
     */
    public void showAddTaskMessage() {
        String addTaskMessage = "     Got it. I've added this task: ";
        System.out.println(addTaskMessage);
    }

    /**
     * Prints the information about a task that has just been added.
     *
     * @param task Task that has just been added to the task list.
     */
    public void printAddedTask(Task task) {
        String result = "";
        switch (task.getType()) {
        case TODO:
            result += "       [T][✗] ";
            break;
        case DEADLINE:
            result += "       [D][✗] ";
            break;
        case EVENT:
            result += "       [E][✗] ";
            break;
        default:
            break;
        }
        result += task;
        System.out.println(result);
    }

    /**
     * Prints a string to the user interface.
     *
     * @param string The text to be printed.
     */
    public void println(String string) {
        System.out.println(string);
    }

    /**
     * Shows the farewell message when the user exits Duke.
     */
    public void showFarewellMessage() {
        showLine();
        String farewellMessage = "     Bye. Hope to see you again soon!";
        System.out.println(farewellMessage);
        showLine();
    }

    /**
     * Gets user input through the console.
     */
    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        return userInput;
    }
}
