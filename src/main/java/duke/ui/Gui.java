package duke.ui;

import duke.core.TaskList;
import duke.gui.DialogBox;
import duke.tasks.Task;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.List;

public class Gui extends VBox implements UiInterface {
    private Image avatar;

    public Gui(Image avatar) {
        this.avatar = avatar;
    }

    public void sendDukeMessage(String dukeMessage) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(dukeMessage, avatar));
    }

    /**
     * Greet user.
     */
    public void greet(boolean fileExists) {
        if (fileExists) {
            this.getChildren().addAll(
                    DialogBox.getDukeDialog("    *** EXISTING FILE LOADED ***\n"
                            + "-------------------------------------------\n"
                            + "     Hello! I'm Duke\n"
                            + "     What can I do for you?\n"
                            + "-------------------------------------------", avatar
                    ));
        } else {
            this.getChildren().addAll(
                    DialogBox.getDukeDialog(
                            "    *** NO EXISTING FILE FOUND ***\n"
                                    + "-------------------------------------------\n"
                                    + "     Hello! I'm Duke\n"
                                    + "     What can I do for you?\n"
                                    + "-------------------------------------------", avatar));

        }
    }

    /**
     * Echo TaskList to user.
     * @param taskList TaskList to be echoed
     */
    public void echoList(TaskList taskList, List<String> archives) {
        String output = "";
        output += "-------------------------------------------\n";
        if (taskList.getSize() == 0) {
            output += "     *** No existing tasks ***     \n";
        } else {
            output += "     Here are the tasks in your list: \n";
            for (int i = 0; i < taskList.getSize(); i++) {
                output += String.format("     %d.%s\n",
                        i + 1, taskList.getTask(i).toString());
            }
        }
        output += "-------------------------------------------\n";
        if (archives.size() == 0) {
            output += "     *** No existing archives ***     \n";
        } else {
            output += "     Here are the existing archives: \n";
            for (String archive : archives) {
                output += "     " + archive;
            }
            output += "\n";
        }
        output += "-------------------------------------------";
        this.getChildren().addAll(
                DialogBox.getDukeDialog(output, avatar));
    }

    /**
     * Echo matching Tasks to user.
     * @param matchingTasks Matching Tasks
     */
    public void echoMatchingTasks(List<Task> matchingTasks) {
        String output = "";
        output += "-------------------------------------------\n";
        if (matchingTasks.size() == 0) {
            output += "     *** List is Empty ***     \n";
        } else {
            output += "     Here are the matching tasks in your list: \n";
            for (int i = 0; i < matchingTasks.size(); i++) {
                output += String.format("     %d.%s\n",
                        i + 1, matchingTasks.get(i).toString());
            }
        }
        output += "-------------------------------------------";
        this.getChildren().addAll(
                DialogBox.getDukeDialog(output, avatar));
    }

    /**
     * Echo added Task to user.
     * @param taskToAdd Added Task
     * @param taskListSize Numer of Tasks in list
     */
    public void echoAddedTask(Task taskToAdd, int taskListSize) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog("-------------------------------------------\n"
                        + "     Got it. I've added this task: \n"
                        + String.format("       %s \n", taskToAdd.toString())
                        + String.format("     Now you have %d tasks in the list.\n", taskListSize)
                        + "-------------------------------------------", avatar));
    }

    /**
     * Echo completed Task to user.
     * @param taskToComplete Completed Task
     */
    public void echoCompletedTask(Task taskToComplete) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(String.format("-------------------------------------------\n"
                                + "     Nice! I've marked this task as done: \n"
                                + "       %s\n"
                                + "-------------------------------------------",
                        taskToComplete.toString()), avatar));
    }

    /**
     * Echo deleted Task to user.
     * @param taskToDelete Deleted Task
     * @param taskListSize Number of remaining Tasks in TaskList
     */
    public void echoDeletedTask(Task taskToDelete, int taskListSize) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(String.format("-------------------------------------------\n"
                                + "     Noted. I've removed this task: \n"
                                + "       %s\n"
                                + "     Now you have %d tasks in the list.\n"
                                + "-------------------------------------------",
                        taskToDelete.toString(), taskListSize), avatar));
    }

    /**
     * Echo Exception to user.
     * @param e Exception to be echoed
     */
    public void echoException(Exception e) {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(e.getMessage(), avatar));
    }

    /**
     * Echo message to user.
     * @param msg Message to be echoed
     */
    public void echoMessage(String msg) {
        String output = "";
        output += "-------------------------------------------\n";
        output += String.format("     *** %s ***     \n", msg);
        output += "-------------------------------------------";
        this.getChildren().addAll(
                DialogBox.getDukeDialog(output, avatar));
    }

    /**
     * Show exit message to user.
     */
    public void exit() {
        this.getChildren().addAll(
                DialogBox.getDukeDialog(
                        "-------------------------------------------\n"
                                + "     Bye. Hope to see you again soon!\n"
                                + "-------------------------------------------", avatar));
    }
}
