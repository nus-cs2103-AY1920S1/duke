package dose.util.gui;

import dose.task.Task;
import dose.task.TaskList;
import dose.util.Ui;
import dose.util.UiMessage;
import dose.util.exception.DoseException;
import dose.util.gui.messagebox.DukeMessageBox;
import dose.util.gui.messagebox.ExceptionMessageBox;
import dose.util.gui.messagebox.MessageBox;
import dose.util.gui.messagebox.TaskMessageBox;
import dose.util.gui.messagebox.UserMessageBox;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Handles Dose's UI when in GUI mode.
 */
public class Gui implements Ui {

    /** Represents the colour scheme in use for the GUI. Hardcoded as MINT for now. */
    private ColourScheme colourScheme = ColourScheme.MINT;

    /** Represents the queue of messages to be displayed in the GUI. */
    // todo: change Queue to Stream
    private Queue<MessageBox> messageBoxQueue = new LinkedList<>();

    /**
     * Not in use for Dose's GUI.
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
    public void showError(DoseException exception) {
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
