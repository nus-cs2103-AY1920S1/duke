package duke.ui;

/**
 * The SpeechMaker class is a container for Snowball's text messages and
 * responses.
 */
public class SpeechMaker {

    /** Message to introduce Snowball. */
    public static final String MESSAGE_SELF_INTRODUCTION = "Hello! My name is Snowball. "
            + "I'm here to help you organise your tasks!\n";

    /** Help message showing a list of basic commands. */
    public static final String MESSAGE_HELP = "Here are some instructions you can give me:\n"
            + "~ list - show all tasks\n"
            + "~ todo [description] - make a new todo\n"
            + "~ event [description] /at DD-MM-YY hh:mm - add a new event at this time\n"
            + "~ deadline [description] /by DD-MM-YY hh:mm - add a new deadline to be completed by this time\n";

    /** Message to say goodbye. */
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";

    /**
     * A welcome message consisting of Snowball's self-introduction
     * and a help message.
     */
    public static final String MESSAGE_WELCOME = MESSAGE_SELF_INTRODUCTION + "\n" + MESSAGE_HELP;

    /**
     * Returns an apology followed by the given message.
     *
     * @param message Message describing the reason for apology.
     * @return String containing apology message.
     */
    public static String getApology(String message) {
        return "Sorry, " + message;
    }

    /**
     * Checks whether the given message is a bye message from Snowball.
     *
     * @param message String representing the message to be checked
     * @return true if the given message is a Snowball bye message, and false
     *         otherwise.
     */
    public static boolean isByeMessage(String message) {
        return MESSAGE_BYE.equals(message);
    }
}
