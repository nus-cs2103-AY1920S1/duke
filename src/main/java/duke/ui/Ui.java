package duke.ui;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Manages UI related functions such as input and output.
 */
public class Ui {
    private Scanner scanner;
    private static Queue<String> messageQueue = new LinkedList<>();

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from system input.
     *
     * @return Input string. null if there is no input.
     */
    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    public void showLine() {
        printToConsole("____________________________________________________________");
    }

    public void showError(String message) {
        printToConsole(message);
    }

    /**
     * Shows a welcome message.
     */
    public void showWelcome() {
        printToConsole(getWelcomeMessage());
    }

    /**
     * Gets a preset welcome message.
     *
     * @return Welcome message
     */
    public static String getWelcomeMessage() {
        return "Hello, I am D O K E."
                + System.lineSeparator()
                + "How can I help you?";
    }

    /**
     * Gets a preset loading error.
     *
     * @return Generic loading error message
     */
    public static String getLoadingError() {
        return "The save file doesn't seem to be there or is formatted incorrectly!"
                + System.lineSeparator()
                + "Let's start afresh.";
    }

    /**
     * Shows a loading error message.
     */
    public void showLoadingError() {
        printLine(getLoadingError());
    }

    /**
     * Queues a message to be printed in a buffer.
     * Call getMessage() to obtain the message.
     *
     * @param message To be printed.
     */
    public void printLine(String message) {
        messageQueue.offer(message);
    }

    /**
     * Queues a message to be printed in a buffer.
     * Call getMessage() to obtain the messages.
     *
     * @param message To be printed.
     */
    public void printLine(Object message) {
        messageQueue.offer(message.toString());
    }

    /**
     * De-queues all messages from the message queue into a single string.
     *
     * @return Message to be printed.
     */
    public String getMessage() {
        StringBuilder output = new StringBuilder();
        while (!messageQueue.isEmpty()) {
            output.append(messageQueue.poll());
            output.append(System.lineSeparator());
        }

        return output.toString().trim();
    }

    /**
     * Checks if there is a new message in the queue.
     *
     * @return True if there is a message in the queue.
     */
    public static boolean hasNewMessage() {
        return !messageQueue.isEmpty();
    }

    /**
     * Clears the message queue.
     */
    public static void clearMessageQueue() {
        messageQueue.clear();
    }

    /**
     * Prints a message.
     */
    public void printToConsole() {
        System.out.println();
    }

    /**
     * Prints a message to console.
     *
     * @param message To be printed.
     */
    public void printToConsole(String message) {
        System.out.println(message);
    }
}
