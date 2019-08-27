package duke.ui;

import java.util.StringJoiner;

public class UI {
    public void showWelcomeMessage() {
        StringJoiner sj = new StringJoiner("\n");
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sj.add("Hello from\n" + logo);
        sj.add("What can I do for you?");
        showMessage(sj.toString());
    }

    public void showByeMessage() {
        String exitMessage = "Bye. Hope to see you again soon!";
        showMessage(padMessage(exitMessage));
    }

    public void showDoneMessage(String taskMessage) {
        String doneMessage = "Nice! I've marked this task as done:";
        StringJoiner result = new StringJoiner("\n");
        result.add(doneMessage);
        result.add(taskMessage);
        showMessage(padMessage(result.toString()));
    }

    public void showDeleteMessage(String taskMessage, int tasksSize) {
        String deleteMessage = "Noted. I've removed this task: ";
        StringJoiner result = new StringJoiner("\n");
        result.add(deleteMessage);
        result.add(taskMessage);
        result.add(String.format("Now you have %d tasks in the list.", tasksSize));
        showMessage(padMessage(result.toString()));
    }

    public void showAddedMessage(String taskMessage, int tasksSize) {
        String addedMessage = "Got it. I've added this task:";
        StringJoiner result = new StringJoiner("\n");
        result.add(addedMessage);
        result.add(taskMessage);
        result.add(String.format("Now you have %d tasks in the list.", tasksSize));
        showMessage(padMessage(result.toString()));
    }

    public void showIllegalCommandMessage() {
        String illegalCommandMessage = "____________________________________________________________\n"
                + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "____________________________________________________________";
        showMessage(illegalCommandMessage);
    }

    public void showLoadingError() {
        String loadingError = "\u2639 OOPS!!! I'm sorry, but file was not loaded :-(\n";
        showMessage(padMessage(loadingError));
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String padMessage(String message) {
        StringJoiner result = new StringJoiner("\n");
        result.add("____________________________________________________________");
        result.add(message);
        result.add("____________________________________________________________");
        return result.toString();
    }
}