package duke.ui;

import java.util.StringJoiner;

/**
 * Prints messages for client.
 */
public class UI {

    /**
     * Shows welcome message.
     */
    public void showWelcomeMessage() {
        StringJoiner sj = new StringJoiner("\n");
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sj.add("Hello from\n" + logo);
        sj.add("What can I do for you?");
        showMessage(sj.toString());
    }

    /**
     * Shows bye message.
     */
    public void showByeMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        showMessage(padMessage(exitMessage));
    }

    /**
     * Shows done message.
     * @param taskMessage done task
     */
    public void showDoneMessage(String taskMessage) {
        String doneMessage = "Nice! I've marked this task as done:";
        StringJoiner result = new StringJoiner("\n");
        result.add(doneMessage);
        result.add(taskMessage);
        showMessage(padMessage(result.toString()));
    }

    /**
     * Shows deleted message.
     * @param taskMessage deleted task
     * @param tasksSize task size after deletion
     */
    public void showDeleteMessage(String taskMessage, int tasksSize) {
        String deleteMessage = "Noted. I've removed this task: ";
        StringJoiner result = new StringJoiner("\n");
        result.add(deleteMessage);
        result.add(taskMessage);
        result.add(String.format("Now you have %d tasks in the list.", tasksSize));
        showMessage(padMessage(result.toString()));
    }

    /**
     * Shows added message.
     * @param taskMessage added task
     * @param tasksSize task size after adding
     */
    public void showAddedMessage(String taskMessage, int tasksSize) {
        String addedMessage = "Got it. I've added this task:";
        StringJoiner result = new StringJoiner("\n");
        result.add(addedMessage);
        result.add(taskMessage);
        result.add(String.format("Now you have %d tasks in the list.", tasksSize));
        showMessage(padMessage(result.toString()));
    }

    /**
     * Shows illegal command message.
     */
    public void showIllegalCommandMessage() {
        String illegalCommandMessage = "____________________________________________________________\n"
                + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "____________________________________________________________";
        showMessage(illegalCommandMessage);
    }

    /**
     * Shows loading error.
     */
    public void showLoadingError() {
        String loadingError = "\u2639 OOPS!!! I'm sorry, but file was not loaded :-(\n";
        showMessage(padMessage(loadingError));
    }

    /**
     * Show message.
     * @param message message
     */
    public void showMessage(String message) {
        System.out.println(message);
    }


    /**
     * Show found message.
     *
     * @param taskMessage tasks found
     */
    public void showFoundMessage(String taskMessage) {
        String foundMessage = "Here are the matching tasks in your list:";
        StringJoiner result = new StringJoiner("\n");
        result.add(foundMessage);
        result.add(taskMessage);
        showMessage(padMessage(result.toString()));
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