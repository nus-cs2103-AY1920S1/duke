package duke.util;

/**
 * Ui class to print display items.
 */
public class Ui {

    /**
     * Prints welcome message.
     */
    public static String welcomeMessage() {
        String result = "I'm Perry the Platypus.\nWhat can I do for you?";
        result += "\nType 'commands' to view a list of commands you can use.";
        return result;
    }

    /**
     * Prints bye message.
     */
    public String byeMessage() {
        String result = "Bye. Hope to see you again soon!";
        return result;
    }
}
