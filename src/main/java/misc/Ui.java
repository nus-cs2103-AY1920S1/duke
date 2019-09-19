package misc;

/**
 * Creates a User Interface that interacts with and displays information to the user.
 */
public class Ui {
    /**
     * Displays a welcome message to the user.
     * @return a String representation of the welcome message.
     */
    public static String welcome() {
        String welcomeMessage = "Hello! I'm Duke!\nWhat can I do for you?";

        return welcomeMessage;
    }

    /**
     * Displays a goodbye message to the user.
     * @return a String representation of the goodbye message.
     */
    static String exit() {
        return "Bye! Hope to see you again soon! ;)";
    }
}
