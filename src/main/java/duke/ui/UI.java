package duke.ui;

import java.util.StringJoiner;

/**
 * Prints messages for client.
 */
public class UI {

    /**
     * Shows welcome message.
     * @return welcome message
     */
    public String getWelcomeMessage() {
        StringJoiner sj = new StringJoiner("\n");
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sj.add("Hello from\n" + logo);
        sj.add("What can I do for you?");
        return sj.toString();
    }

    /**
     * Returns bye message.
     * @return bye message
     */
    public String getByeMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        return exitMessage;
    }

    /**
     * Gets done message.
     * @param taskMessage done task
     * @return done message
     */
    public String getDoneMessage(String taskMessage) {
        String doneMessage = "Nice! I've marked this task as done:";
        StringJoiner result = new StringJoiner("\n");
        result.add(doneMessage);
        result.add(taskMessage);
        return result.toString();
    }

    /**
     * Gets deleted message.
     * @param taskMessage deleted task
     * @param tasksSize task size after deletion
     * @return deleted message
     */
    public String getDeleteMessage(String taskMessage, int tasksSize) {
        String deleteMessage = "Noted. I've removed this task: ";
        StringJoiner result = new StringJoiner("\n");
        result.add(deleteMessage);
        result.add(taskMessage);
        result.add(String.format("Now you have %d tasks in the list.", tasksSize));
        return result.toString();
    }

    /**
     * Gets added message.
     * @param taskMessage added task
     * @param tasksSize task size after adding
     * @return added message
     */
    public String getAddedMessage(String taskMessage, int tasksSize) {
        String addedMessage = "Got it. I've added this task:";
        StringJoiner result = new StringJoiner("\n");
        result.add(addedMessage);
        result.add(taskMessage);
        result.add(String.format("Now you have %d tasks in the list.", tasksSize));
        return result.toString();
    }

    /**
     * Gets illegal command message.
     * @return illegal command message
     */
    public String getIllegalCommandMessage() {
        String illegalCommandMessage = "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
        return illegalCommandMessage;
    }

    /**
     * Gets loading error.
     * @return loading error
     */
    public String getLoadingError() {
        String loadingError = "\u2639 OOPS!!! I'm sorry, but file was not loaded :-(";
        return loadingError;
    }

    /**
     * Show message.
     * @param message message
     */
    public void showMessage(String message) {
        System.out.println(padMessage(message));
    }


    /**
     * Gets found message.
     *
     * @param taskMessage tasks found
     * @return Found Message
     */
    public String getFoundMessage(String taskMessage) {
        String foundMessage = "Here are the matching tasks in your list:";
        StringJoiner result = new StringJoiner("\n");
        result.add(foundMessage);
        result.add(taskMessage);
        return result.toString();
    }

    /**
     * Pads message with horizontal lines.
     *
     * @param message message
     * @return padded message
     */
    public String padMessage(String message) {
        StringJoiner result = new StringJoiner("\n");
        result.add("____________________________________________________________");
        result.add(message);
        result.add("____________________________________________________________");
        return result.toString();
    }
}