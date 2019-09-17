package duke.ui;

/**
 * The SpeechMaker class is a container for Snowball's text messages and
 * responses.
 */
public class SpeechMaker {

    /** Message to introduce Snowball. */
    public static final String SELF_INTRODUCTION_MESSAGE = "Hello! My name is Snowball. "
            + "I'm here to help you organise your tasks!\n";

    /** Help message showing a list of basic commands. */
    public static final String HELP_MESSAGE = "Here are some instructions you can give me:\n"
            + "~ list - show all tasks\n"
            + "~ todo [description] - make a new todo\n"
            + "~ event [description] /at DD-MM-YY hh:mm - add a new event at this time\n"
            + "~ deadline [description] /by DD-MM-YY hh:mm - add a new deadline to be completed by this time\n";

    /** Message to say goodbye. */
    public static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Returns a welcome message consisting of Snowball's self-introduction
     * and a help message.
     *
     * @return String containing a self-introduction and help message.
     */
    public static String getWelcomeMessage() {
        return SELF_INTRODUCTION_MESSAGE + "\n" + HELP_MESSAGE;
    }

    /**
     * Returns an apology followed by the given message.
     *
     * @param message Message describing the reason for apology.
     * @return String containing apology message.
     */
    public static String getApology(String message) {
        return "Sorry, " + message;
    }

}
