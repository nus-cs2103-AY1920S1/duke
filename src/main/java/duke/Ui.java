package duke;

import duke.Parser;
import task.Task;

import java.util.Scanner;

/**
 * Deals with user interaction for Duke
 */
public class Ui {
    public static final String LINE = "    ____________________________________________________________\n";

    /**
     * Greets the user with default text.
     */
    public void greet() {
        String greeting = LINE
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + LINE;

        System.out.println(greeting);
    }

    public void showLine() {
        System.out.print(LINE);
    }

    public void showAddTaskMessage() {
        String addTaskMessage = "     Got it. I've added this task: ";
        System.out.println(addTaskMessage);
    }

    public String printAddedTask(Task task) {
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
        }
        result += task;
        // System.out.println(result);

        return result;
    }

    public void println(String string) {
        System.out.println(string);
    }

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
