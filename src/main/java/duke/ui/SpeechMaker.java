package duke.ui;

/**
 * The SpeechMaker class contains all of Snowball's messages and responses.
 */
public class SpeechMaker {

    /** A template to display tasks with indentation using String.format. */
    private static final String TEMPLATE_SHOW_TASK = "  %1$s\n";

    /** Message to introduce Snowball. */
    public static final String MESSAGE_SELF_INTRODUCTION = "Hello! My name is Snowball. "
            + "I'm here to help you organise your tasks!\n";
    /** Help message showing a list of basic commands. */
    public static final String MESSAGE_HELP = "Here are some instructions you can give me:\n"
            + "~ list - show all tasks\n"
            + "~ todo [description] - make a new todo\n"
            + "~ event [description] /at DD-MM-YY hh:mm - add a new event at this time\n"
            + "~ deadline [description] /by DD-MM-YY hh:mm - add a new deadline to be completed by this time\n";

    /**
     * Welcome message consisting of Snowball's self-introduction and a help message.
     */
    public static final String MESSAGE_WELCOME = MESSAGE_SELF_INTRODUCTION + "\n" + MESSAGE_HELP;
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!\n";
    public static final String MESSAGE_TASK_ADDED =
            "Got it. I've added this task:\n" + TEMPLATE_SHOW_TASK;
    public static final String MESSAGE_TASK_DELETED =
            "Noted. I've removed this task:\n" + TEMPLATE_SHOW_TASK;
    public static final String MESSAGE_TASK_DONE =
            "Nice! I've marked this task as done:\n" + TEMPLATE_SHOW_TASK;
    public static final String MESSAGE_TASK_UNDONE =
            "Oh dear. I've marked this task as undone:\n" + TEMPLATE_SHOW_TASK;
    public static final String MESSAGE_SET_PRIORITY =
            "Okay! I've set this task's priority to %2$s:\n" + TEMPLATE_SHOW_TASK;

    /**
     * Gets an apology message from Snowball for the reason given in {@code message}.
     *
     * @param message A String containing the reason for apology.
     * @return String containing apology message.
     */
    public static String getApologyMessage(String message) {
        return "Sorry, " + message;
    }

    /**
     * Gets a message from Snowball that describes the number of tasks in the
     * task list.
     *
     * @param numberOfTasks The number of tasks in the list.
     * @return Message describing the number of tasks in the list.
     */
    public static String getNumberOfTasksMessage(int numberOfTasks) {
        String taskPlural = numberOfTasks == 1 ? "" : "s";
        return "Now you have " + numberOfTasks + " task" + taskPlural + " in the list.\n";
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
