package duke;

public class Ui {

    /**
     * Creates a new Ui object.
     */
    public Ui() {
    }

    /**
     * Returns the greeting message.
     *
     * @return Greeting message.
     */
    public static String getGreeting() {
        return "Hello! I'm Duke\nWhat can i do for you?";
    }

    /**
     * Returns the modified string.
     *
     * @param s String to be modified.
     * @return Modified string.
     */
    public static String returnOutput(String s) {
        return s.replace("\n", "\n    ");
    }

    /**
     * Returns the modified string.
     *
     * @param s           String to be modified.
     * @param taskMessage Message returned from task.
     * @param listSize    Size of the task list.
     * @return Modified string.
     */
    public static String returnOutput(String s, String taskMessage, int listSize) {
        return taskMessage + "\n" + s.replace("\n", "\n    ") + "\n" + "Now you have " + listSize
            + " tasks in the list.";
    }
}
