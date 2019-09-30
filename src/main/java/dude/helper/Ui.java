package dude.helper;

import dude.fxgui.DialogBox;
import dude.task.Task;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading from user input and printing to user interface.
 */
public class Ui {
    private VBox dialogContainer;
    private TextField userInput;
    public Image user = new Image(this.getClass().getResourceAsStream("/images/userSleipnir.png"));
    public Image duke = new Image(this.getClass().getResourceAsStream("/images/dukeGhidorah.jpg"));

    /**
     * Initializes UI instance with a VBox that contains dialog boxes and TextField for user input.
     *
     * @param dialogContainer VBox that UI should write to.
     * @param userInput TextField that UI should read user input from.
     */
    public Ui(VBox dialogContainer, TextField userInput) {
        this.dialogContainer = dialogContainer;
        this.userInput = userInput;
    }

    public Ui(TextField userInput) {
        this.userInput = userInput;
    }

    /**
     * Displays welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeLine = logo + "\nHello! I'm DUDE!\nWhat can I do for you?\n";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeLine, duke));
    }

    public String readInput() {
        return userInput.getText();
    }

    public void printUserInput() {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(readInput(), user));
        userInput.clear();
    }

    public void showLoadingError() {
        String message = "Error loading from file. Initiating with empty Task list.";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, duke));
    }

    public void printError(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, duke));
    }

    /**
     * Displays list of existing tasks.
     *
     * @param tasks ArrayList of Tasks to be displayed.
     */
    public void printTaskList(ArrayList<Task> tasks) {
        String list = "Here are the tasks in your list:";
        for (Task task : tasks) {
            list += "\n" + (tasks.indexOf(task) + 1) + ". " + task.toString();
        }
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(list, duke));
    }

    public void printDoneNotification(String doneTask) {
        String notification = "Nice! I've marked this task as done:\n" + "    " + doneTask;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(notification, duke));
    }

    /**
     * Prints notification when task is added to the list.
     *
     * @param addTask Task to be added.
     * @param listSize Current size of the list.
     */
    public void printAddNotification(String addTask, int listSize) {
        String notification = "Got it. I've added this task:\n" + "    " + addTask + "\nNow you have " + listSize
                + " tasks in the list.";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(notification, duke));
    }

    /**
     * Prints notification when task is deleted from the list.
     *
     * @param delTask Task to be deleted.
     * @param listSize Current size of the list.
     */
    public void printDeleteNotification(String delTask, int listSize) {
        String notification = "Noted. I've removed this task:\n" + "    " + delTask + "\nNow you have " + listSize
                + " tasks in the list.";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(notification, duke));
    }

    /**
     * Prints notification when item is updated with new details. Prints old details as well for comparison.
     *
     * @param updatingTask Task to be updated.
     * @param updateType Type of detail to be updated: "desc" for all Tasks or "time for Event and Deadline.
     * @param originalValue Original value of detail that was updated.
     */
    public void printUpdateNotification(String updatingTask, String updateType, String originalValue) {
        String notification = "I've updated this task's " + updateType + ":\n    " + updatingTask
                + "\n\n Original " + updateType + ": \n" + originalValue;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(notification, duke));
    }

    public void printExitMessage() {
        String exit = "Bye. Hope to see you again soon!\n";
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(exit, duke));
    }

    /**
     * Displays list of tasks produced from the FindCommand.
     *
     * @param tasks ArrayList of Tasks to be displayed.
     */
    public void printSearchList(List<Task> tasks) {
        String list = "Here are the matching tasks in your list:";
        for (Task task : tasks) {
            list += "\n" + (tasks.indexOf(task) + 1) + ". " + task.toString();
        }
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(list, duke));
    }
}
