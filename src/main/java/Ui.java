import java.util.Scanner;

/**
 * Scans input from the user and prints feedback to the user.
 */
public class Ui {
    /**
     * Scanner object used for reading user input.
     */
    private Scanner scanner;
    
    /**
     * Creates a Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }
    
    /**
     * Scans the next input line by the user.
     *
     * @return Returns the entire input line as a String.
     */
    String getNextLine() {
        return scanner.nextLine();
    }
    
    /**
     * Displays the error message of an Exception.
     *
     * @param exception The Exception to display the error message of.
     */
    void showError(Exception exception) {
        System.out.println(exception.getMessage());
    }
    
    /**
     * Prints a welcome message for the user.
     */
    void printHello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }
    
    /**
     * Prints a goodbye message for the user.
     */
    void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    /**
     * Prints the current number of Tasks stored by the user.
     *
     * @param size The number of Tasks currently stored.
     */
    void printSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }
    
    /**
     * Tests for whether the description of a given Task is empty.
     * If empty, displays an exception message.
     * A description consisting only of whitespace is considered empty.
     *
     * @param taskDescription The description of the given Task.
     * @throws EmptyTaskDescriptionException A DukeException thrown to indicate an empty Task description.
     */
    void testEmptyDescription(String taskDescription) throws EmptyTaskDescriptionException {
        if (taskDescription.matches("\\s*")) {
            throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
    }
    
    /**
     * Tests for whether the time in a given task is correctly formatted.
     * Valid time formats require a ' /by ' or ' /at ' to be present in the String.
     *
     * @param taskDescription The description of the given Task.
     * @throws IncorrectTaskTimeFormatException A DukeException thrown to indicate an incorrect format.
     */
    void testTimeFormat(String taskDescription) throws IncorrectTaskTimeFormatException {
        if (!(taskDescription.contains(" /by ") || taskDescription.contains(" /at "))) {
            throw new IncorrectTaskTimeFormatException("OOPS!!! No ' /by ' or ' /at ' detected!"
                    + "Please use the correct format!");
        }
    }
}
