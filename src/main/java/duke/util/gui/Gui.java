package duke.util.gui;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;
import duke.util.UiMessage;
import duke.util.exception.DukeException;
import duke.util.gui.messagebox.DukeMessageBox;
import duke.util.gui.messagebox.ExceptionMessageBox;
import duke.util.gui.messagebox.MessageBox;
import duke.util.gui.messagebox.TaskMessageBox;
import duke.util.gui.messagebox.UserMessageBox;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Handles Duke's UI when in GUI mode.
 */
public class Gui implements Ui {

    /** Represents the colour scheme in use for the GUI. Hardcoded as MINT for now. */
    private ColourScheme colourScheme = ColourScheme.MINT;

    /** Represents the queue of messages to be displayed in the GUI. */
    private Queue<MessageBox> messageBoxQueue = new LinkedList<>();

    /**
     * Not in use for Duke's GUI.
     * @return Nothing.
     */
    @Override
    public String readCommand() {
        return null;
    }

    public void showUserInput(String input) {
        try {
            messageBoxQueue.add(new UserMessageBox(input, this.colourScheme));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(UiMessage uiMessage) {
        try {
            messageBoxQueue.add(new DukeMessageBox(uiMessage.getMessage(), this.colourScheme));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message) {
        try {
            messageBoxQueue.add(new DukeMessageBox(message, this.colourScheme));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showError(DukeException exception) {
        try {
            messageBoxQueue.add(new ExceptionMessageBox(exception.getMessage(), this.colourScheme));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showTasks(TaskList tasks) {
        ArrayList<Task> listOfTasks = tasks.getTasks();
        for (Task task : listOfTasks) {
            showTask(task, tasks);
        }
    }

    public void showTask(Task task, TaskList tasks) {
        String taskString = task.toString();
        int taskIdString = tasks.getId(task);
        String toPrint = taskIdString + ". " + taskString;
        messageBoxQueue.add(new TaskMessageBox(toPrint, this.colourScheme));
    }

    public Queue<MessageBox> getMessageBoxQueue() {
        return messageBoxQueue;
        // todo: move clear() to here, makes more sense
    }
}
