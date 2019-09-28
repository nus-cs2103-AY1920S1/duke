package duke;

public class Ui {

    /**
     * Prints once the program duke.Duke.java is launched.
     */
    public static String getWelcomeMessage() {
        return "Hello! I'm duke.Duke\n" + "What can I do for you?";
    }

    /**
     * Prints right before the program duke.Duke.java is closed.
     */
    public static String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out message after exceeding list bounds.
     * @param size - Current size of list
     */
    public static String getExceedListMessage(int size) {
        return "There is/are only " + size + " item(s) in the list :( ";
    }

    /**
     * Prints out message after invalid statement.
     * @param fullCommand - Invalid command
     */
    public static String getInvalidStatementMessage(String fullCommand) {
        return "OOPS!!! The statement: \"" + fullCommand + "\" is invalid. ";
    }
}
