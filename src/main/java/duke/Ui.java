package duke;

import duke.task.Task;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/bot.jpg"));

    /**
     * Gets a String of all the tasks in a given TaskList.
     * @param tasks The TaskList containing the tasks to be printed.
     * @return A String of all given tasks, with each task on one row.
     */
    private String getTasksString(TaskList tasks) {
        StringBuilder tasksString = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            tasksString.append(String.format("%d.%s", i, tasks.get(i)));
            if (i != tasks.size()) {
                tasksString.append("\n");
            }
        }
        return tasksString.toString();
    }

    /**
     * Shows the welcome message as a message from Duke.
     * @param dialogContainer The VBox container for all dialog boxes.
     */
    public void showWelcomeMessage(VBox dialogContainer) {
        String welcomeMessage = "Hello! I'm Duke. What can I do for you?";

        DialogBox dukeDialogBox = DialogBox.getDukeDialog(
                new Label(welcomeMessage), new ImageView(duke)
        );

        dialogContainer.getChildren().add(dukeDialogBox);
    }

    /**
     * Shows information about how the user should format datetime inputs.
     * @param dialogContainer The VBox container for all dialog boxes.
     */
    public void showDateTimeFormatMessage(VBox dialogContainer) {
        String datetimeInfo = "To input dates and times for deadlines and events, "
                + "please use the format: 29/03/2019, 6:05pm";
        showDukeMessage(datetimeInfo, dialogContainer);
    }

    /**
     * Gets the app's exit message.
     * @return The exit message as a String.
     */
    public String getExitMessage() {
        return "Bye! Hope to see you again soon :)";
    }

    /**
     * Shows the user's input as a message from the user.
     * @param input The user's input as a String.
     * @param dialogContainer The VBox container for all dialog boxes.
     */
    public void showUserInput(String input, VBox dialogContainer) {
        Label userText = new Label(input);
        DialogBox userDialogBox = DialogBox.getUserDialog(userText, new ImageView(user));
        dialogContainer.getChildren().add(userDialogBox);
    }

    /**
     * Shows a message from Duke to the user.
     * @param message Duke's message as a String.
     * @param dialogContainer The VBox container for all dialog boxes.
     */
    public void showDukeMessage(String message, VBox dialogContainer) {
        Label dukeMessage = new Label(message);
        DialogBox dukeDialogBox = DialogBox.getDukeDialog(dukeMessage, new ImageView(duke));
        dialogContainer.getChildren().add(dukeDialogBox);
    }

    /**
     * Prints each Task in the given TaskList, if any.
     * @param tasks The TaskList containing the tasks to print.
     */
    public String getTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list right now.";
        } else {
            return "Here are the task(s) in your list:" + getTasksString(tasks);
        }
    }

    /**
     * Prints the confirmation message for marking a Task as done.
     * @param task The Task marked as done.
     */
    public String getTaskMarkedDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n  " + task;
    }

    /**
     * Prints the confirmation message for deleting a Task from the TaskList.
     * @param tasks The TaskList, containing the remaining tasks.
     * @param task The deleted Task.
     */
    public String getTaskDeletedMessage(TaskList tasks, Task task) {
        return String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        );
    }

    /**
     * Prints the confirmation message for adding a new Task to the TaskList.
     * @param tasks The TaskList, containing all tasks including the newly added Task.
     * @param task The newly added Task.
     */
    public String getTaskAddedMessage(TaskList tasks, Task task) {
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.",
                task, tasks.size()
        );
    }

    /**
     * Prints the results of a user search query.
     * @param results A TaskList of results matching the user's query.
     */
    public String getSearchResults(TaskList results) {
        if (results.size() == 0) {
            return "No matching tasks found in your list.";
        } else {
            return "Here are the matching tasks in your list:\n" + getTasksString(results);
        }
    }
}
