package misc;

/**
 * Creates a User Interface that interacts with and displays information to the user.
 */
public class Ui {
    /**
     * Displays the Duke Logo.
     * @return a String representation of Duke Logo.
     */
    public String showDukeLogo() {
        String dukeLogo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        return dukeLogo;
    }

    /**
     * Displays a welcome message to the user.
     * @return a String representation of the welcome message.
     */
    public static String welcome() {
        String welcomeMessage = "Hello! I'm Duke!\nWhat can I do for you?";

        return welcomeMessage;
    }

    /**
     * Displays a welcomeBack message to the user.
     * This occurs if there are existing tasks in a local save file from last session.
     * @return a String representation of the welcome back message.
     */
    public static String welcomeBack() {
        String welcomeMessage = "Hello! Welcome back!\n"
                + "Carrying off from where you left behind the last time...";

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
