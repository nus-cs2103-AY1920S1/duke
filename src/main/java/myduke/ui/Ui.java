package myduke.ui;

import myduke.task.Task;
import myduke.type.LoggerMessageType;
import myduke.type.MessageFormatType;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * Handles all user interactions.
 */
public class Ui {
    //Constants
    private static final String MESSAGE_PADDING  = "     ";
    private static final String MESSAGE_BOUNDARY = "    ____________________________________________________________";
    private static final String MESSAGE_NEWLINE  = System.lineSeparator();
    private static final char MESSAGE_SAD_FACE   = 0x2639;

    //Class variables
    private Scanner primaryScanner;
    private Consumer<String> logger;
    private LinkedList<String> messages;
    private MessageFormatType defaultMessageFormat;

    /**
     * Constructor for Ui.
     *
     * @param logger A consumer to print the message.
     * @param format The default format to print the message.
     */
    public Ui(Consumer<String> logger, MessageFormatType format) {
        this.defaultMessageFormat = format;
        this.messages = new LinkedList<>();
        this.logger = logger;
    }

    /**
     * Initialises the scanner to read queries from the console.
     */
    public void initScanner() {
        if (!isConsoleScannerInitialised()) {
            primaryScanner = new Scanner(System.in);  // Create a Scanner object
        }
    }

    /**
     * Checks whether the console scanner has been initialised.
     *
     * @return A boolean representing whether the console scanner has been initialised.
     */
    public boolean isConsoleScannerInitialised() {
        return primaryScanner != null;
    }

    /**
     * Waits for a user query.
     *
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
     *
     * @param responseHeader A message to the user.
     */
    public void printResponse(String responseHeader) {
        messages.add(responseHeader);
    }

    /**
     * Prints a response and list the given tasks.
     *
     * @param responseHeader A message to the user.
     * @param listOfTasks    The list of tasks to be displayed to the user.
     */
    public void printResponse(String responseHeader, List<Task> listOfTasks) {
        messages.add(responseHeader);
        IntStream
                .range(1, listOfTasks.size() + 1)
                .mapToObj(idx -> String.format("%d.%s", idx, listOfTasks.get(idx - 1)))
                .forEach(messages::add);
    }

    /**
     * Prints a single response and list a specified task.
     *
     * @param responseHeader A message to the user.
     * @param numOfTasksLeft Displays the number of tasks left in the task list if value is greater or equal than 0.
     * @param listOfTasks    The list of tasks to be displayed to the user.
     */
    public void printResponse(String responseHeader, int numOfTasksLeft, Task... listOfTasks) {
        messages.add(responseHeader);

        if (listOfTasks.length == 1) {
            messages.add(listOfTasks[0].toString());
        } else if (listOfTasks.length > 1) {
            IntStream
                    .range(1, listOfTasks.length + 1)
                    .mapToObj(idx -> String.format("%d.%s", idx, listOfTasks[idx - 1]))
                    .forEach(messages::add);
        }

        if (numOfTasksLeft >= 0) {
            messages.add(String.format(
                    "Now you have %d task%s in the list.",
                    numOfTasksLeft,
                    (numOfTasksLeft > 1) ? "s" : ""));
        }
    }

    /**
     * Prints an informational-level message to the console.
     *
     * @param message an informational-level message.
     */
    public void logInfo(String message) {
        printResponse("[INFO]: " + message);
    }

    /**
     * Prints a warning-level message to the console.
     *
     * @param message a warning-level message.
     */
    public void logWarn(String message) {
        printResponse("[WARNING]: " + message);
    }

    /**
     * Prints an error-level message to the console.
     *
     * @param message an error-level message.
     */
    public void logError(String message) {
        printResponse(String.format("%c OOPS!!! %s", MESSAGE_SAD_FACE, message));
    }

    /**
     * Prints the message to the console.
     *
     * @param message a message.
     * @param level   severity of the message.
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

    /**
     * Displays the logged messages in the default message format.
     *
     * @return The printed message.
     */
    public String displayMessage() {
        return displayMessage(this.defaultMessageFormat);
    }

    /**
     * Displays the logged messages in a given format type.
     *
     * @param format The format which the message will be printed.
     *
     * @return The printed message.
     */
    public String displayMessage(MessageFormatType format) {
        boolean printBoundary = (format == MessageFormatType.MESSAGE_FORMAT_WITH_BOUNDARY_AND_INDENT);
        boolean printIndent = (format != MessageFormatType.MESSAGE_FORMAT_NO_BOUNDARY_WITHOUT_INDENT);

        StringBuilder builder = new StringBuilder();
        if (printBoundary) {
            builder.append(MESSAGE_BOUNDARY);
            builder.append(MESSAGE_NEWLINE);
        }

        messages.stream()
                .map(msg -> String.format("%s%s%s", (printIndent ? MESSAGE_PADDING : ""), msg, MESSAGE_NEWLINE))
                .forEach(builder::append);
        messages.clear();

        if (printBoundary) {
            builder.append(MESSAGE_BOUNDARY);
            builder.append(MESSAGE_NEWLINE);
        }

        String output = builder.toString();

        logger.accept(output);
        return output;
    }
}
