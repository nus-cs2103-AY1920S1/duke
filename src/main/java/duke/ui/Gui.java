package duke.ui;

import duke.core.TaskList;
import duke.gui.DialogBox;
import duke.tasks.Task;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.List;

public class Gui extends VBox implements UiInterface {
    private Image duke = new Image(this.getClass().getResourceAsStream("../../images/avatar_placeholder.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("../../images/avatar_placeholder.png"));

    public void sendDukeMessage(String dukeMessage) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(dukeMessage, duke));
    }

    /**
     * Greet user.
     */
    public void greet(boolean fileExists) {
        if (fileExists) {
            this.getChildren().addAll(
                    DialogBox.getDukeDialog("    *** EXISTING FILE LOADED ***    "
                            + "_____________________________________________________\n"
                            + "     Hello! I'm Duke\n"
                            + "     What can I do for you?\n"
                            + "_____________________________________________________", duke
                    ));
        } else {
            this.getChildren().addAll(
                    DialogBox.getDukeDialog(
                            "    *** NO EXISTING FILE FOUND ***"
                                    + " _____________________________________________________\n"
                                    + "     Hello! I'm Duke\n"
                                    + "     What can I do for you?\n"
                                    + " _____________________________________________________", duke));

        }
    }

    /**
     * Echo TaskList to user.
     * @param taskList TaskList to be echoed
     */
    public void echoList(TaskList taskList) {
        String output = "";
        output += "_____________________________________________________\n";
        if (taskList.getSize() == 0) {
            output += "     *** List is Empty ***     \n";
        } else {
            output += "     Here are the tasks in your list: \n";
            for (int i = 0; i < taskList.getSize(); i++) {
                output += String.format("     %d.%s\n",
                        i + 1, taskList.getTask(i).toString());
            }
        }
        output += "_____________________________________________________";
        this.getChildren().addAll(
                DialogBox.getDukeDialog(output, duke));
    }

    /**
     * Echo matching Tasks to user.
     * @param matchingTasks Matching Tasks
     */
    public void echoMatchingTasks(List<Task> matchingTasks) {
        String output = "";
        output += "_____________________________________________________\n";
        if (matchingTasks.size() == 0) {
            output += "     *** List is Empty ***     \n";
        } else {
            output += "     Here are the matching tasks in your list: \n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                output += String.format("     %d.%s\n",
                        i + 1, matchingTasks.get(i).toString());
            }
        }
        output += "_____________________________________________________";
        this.getChildren().addAll(
                DialogBox.getDukeDialog(output, duke));
    }

    /**
     * Echo added Task to user.
     * @param taskToAdd Added Task
     * @param taskListSize Numer of Tasks in list
     */
    public void echoAddedTask(Task taskToAdd, int taskListSize) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog("_____________________________________________________\n"
                        + "     Got it. I've added this task: \n"
                        + String.format("       %s \n", taskToAdd.toString())
                        + String.format("     Now you have %d tasks in the list.\n", taskListSize)
                        + "_____________________________________________________", duke));
    }

    /**
     * Echo completed Task to user.
     * @param taskToComplete Completed Task
     */
    public void echoCompletedTask(Task taskToComplete) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(String.format("_____________________________________________________\n"
                                + "     Nice! I've marked this task as done: \n"
                                + "       %s\n"
                                + "_____________________________________________________",
                        taskToComplete.toString()), duke));
    }

    /**
     * Echo deleted Task to user.
     * @param taskToDelete Deleted Task
     * @param taskListSize Number of remaining Tasks in TaskList
     */
    public void echoDeletedTask(Task taskToDelete, int taskListSize) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(String.format("_____________________________________________________\n"
                                + "     Noted. I've removed this task: \n"
                                + "       %s\n"
                                + "     Now you have %d tasks in the list.\n"
                                + "_____________________________________________________",
                        taskToDelete.toString(), taskListSize), duke));
    }

    /**
     * Echo Exception to user.
     * @param e Exception to be echoed
     */
    public void echoException(Exception e) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(e.getMessage(), duke));
    }

    /**
     * Echo message to user.
     * @param msg Message to be echoed
     */
    public void echoMessage(String msg) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(msg, duke));
    }

    /**
     * Show exit message to user.
     */
    public void exit() {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(
                        "_____________________________________________________\n"
                                + "     Bye. Hope to see you again soon!\n"
                                + "_____________________________________________________", duke));
    }
}
