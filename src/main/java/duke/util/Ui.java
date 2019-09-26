package duke.util;

public class Ui {

    public Ui() {
    }

    /**
     * Provides the welcome message to be shown.
     */
    public static String showWelcome() {
        String message = String.format("Hello! I'm Nick.%sWhat can I OOP for you today?", System.lineSeparator());
        return message;
    }

    /**
     * Generates the text to show items loaded from database.
     *
     * @param size Number of items loaded from the database
     */
    public static String showLoading(int size) {
        String message = (size + (size == 1 ? " task" : " tasks") + " were loaded successfully.");
        return message;
    }

    /**
     * Provides the error message.
     *
     * @param message The contents of the error message
     */
    public static String showError(String message) {
        return message;
    }

    /**
     * Provides the goodbye message to be shown.
     */
    public static String showBye() {
        return "Goodbye... Until more OOP.";
    }

    /**
     * Provides the help text to be shown.
     */
    public static String showHelp() {
        String message = String.format("Hello! I'm Nick! You can use:%stodo <item>%sdeadline <item> /by <when>"
                        + "%sevent <item> /at <when>%sfind <keyword>%sdone <number>%sdelete <number>%sstats%sbye", System.lineSeparator(), System.lineSeparator(),
                System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator(), System.lineSeparator());
        return message;
    }

}
