package myduke.core;

import java.util.List;
import java.util.Scanner;

import myduke.task.Task;
import myduke.type.LoggerMessageType;

public class Ui {
    //Constants
    private static final String MESSAGE_PADDING  = "     ";
    private static final String MESSAGE_BOUNDARY = "    ____________________________________________________________";
    private static final char MESSAGE_SAD_FACE = 0x2639;

    //Class variables
    private Scanner primaryScanner;

    /**
     * Initialises the scanner to read queries from the console.
     */
    public void init() {
        if (primaryScanner == null) {
            primaryScanner = new Scanner(System.in);  // Create a Scanner object
        }
    }

    /**
     * Waits for a user query.
     * @return the user query.
     */
    public String waitForQuery() {
        return primaryScanner.nextLine();
    }

    /**
     * Shuts down the scanner used to read queries from the console.
     */
    public void shutdown() {
        if (primaryScanner != null) {
            primaryScanner.close();
            primaryScanner = null;
        }
    }

    /**
     * Prints a single response.
     * @param responseHeader A message to the user.
     */
    public void printResponse(String responseHeader) {
        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);

        System.out.println(responseHeader);

        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println();
    }

    /**
     * Prints a response and list the given tasks.
     * @param responseHeader A message to the user.
     * @param listOfTasks The list of tasks to be displayed to the user.
     */
    public void printResponse(String responseHeader, List<Task> listOfTasks) {
        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);
        System.out.println(responseHeader);

        if (listOfTasks != null) {
            int indexOfTask = 0;
            for (Task currentTask : listOfTasks) {

                indexOfTask += 1;
                System.out.print(MESSAGE_PADDING);
                System.out.print(String.format("%d.", indexOfTask));
                System.out.println(currentTask);
            }
        }

        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println();
    }

    /**
     * Prints a single response and list a specified task.
     * @param responseHeader A message to the user.
     * @param refTask The task to be displayed to the user.
     * @param numOfTasks Displays the number of task(s) left in the task list if value is greater or equal than 0.
     */
    public void printResponse(String responseHeader, Task refTask, int numOfTasks) {
        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.print(MESSAGE_PADDING);
        System.out.println(responseHeader);

        System.out.print(MESSAGE_PADDING);
        System.out.print("  ");
        System.out.println(refTask);

        if (numOfTasks >= 0) {
            System.out.print(MESSAGE_PADDING);
            System.out.println(String.format(
                    "Now you have %d task%s in the list.",
                    numOfTasks,
                    (numOfTasks > 1) ? "s" : ""));
        }

        //Print Boundary
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println();
    }

    /**
     * Greets the user.
     */
    public void welcomeUser() {
        printResponse("Hello! I'm Duke\n" + MESSAGE_PADDING + "What can I do for you?");
    }

    /**
     * Bids farewell to the user.
     */
    public void sayGoodBye() {
        printResponse("Bye. Hope to see you again soon!");
    }

    /**
     * Prints an informational-level message to the console.
     * @param message an informational-level message.
     */
    public void logInfo(String message) {
        printResponse("[INFO]: " + message);
    }

    /**
     * Prints a warning-level message to the console.
     * @param message a warning-level message.
     */
    public void logWarn(String message) {
        printResponse("[WARNING]: " + message);
    }

    /**
     * Prints an error-level message to the console.
     * @param message an error-level message.
     */
    public void logError(String message) {
        printResponse(String.format("%c OOPS!!! %s", MESSAGE_SAD_FACE, message));
    }

    /**
     * Prints the message to the console.
     * @param message a message.
     * @param level severity of the message.
     */
    public void log(String message, LoggerMessageType level) {
        switch (level) {
        case LOGGER_MESSAGE_INFO:
            logInfo(message);
            break;
        case LOGGER_MESSAGE_WARNING:
            logWarn(message);
            break;
        case LOGGER_MESSAGE_ERROR:
        default:
            logError(message);
            break;
        }
    }
}
