package duke.util.gui;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Ui;
import duke.util.UiMessage;
import duke.util.exception.DukeException;
import duke.util.gui.messagebox.MessageBox;
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

    @Override
    public void showMessage(UiMessage uiMessage) {
        try {
            messageBoxQueue.add(MessageBox.getDukeMessageBox(uiMessage.getMessage(), this.colourScheme));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showError(DukeException exception) {
        // todo: replace with custom exception message box once created
        try {
            messageBoxQueue.add(MessageBox.getDukeMessageBox(exception.getMessage(), this.colourScheme));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showTasks(TaskList tasks) {
        showMessage(UiMessage.MATCHING_TASKS);
        ArrayList<Task> listOfTasks = tasks.getTasks();
        for (Task task : listOfTasks) {
            showTask(task);
        }
    }

    public void showTask(Task task) {
        messageBoxQueue.add(MessageBox.getDukeMessageBox(task.toString(), this.colourScheme));
    }

    public Queue<MessageBox> getMessageBoxQueue() {
        return messageBoxQueue;
        // todo: move clear() to here, makes more sense
    }
}
