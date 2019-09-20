package commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import exceptions.DukeException;

import tasks.Task;

import java.util.ArrayList;

/**
 * DoneCommand is a class that marks the specified task
 * item in the list of tasks as done.
 * These items can be ToDo, Event or Deadline tasks.
 */
public class DoneCommand extends UndoableCommand {

    /** The Task that was marked as done following the execution of this done command. */
    Task doneTask;

    /**
     * Constructor for DoneCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public DoneCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Marks the specified task as completed.
     * The number of the task to be marked is specified
     * by the user via the user input.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> taskLst = tasks.getTaskLst();
        if (commandArr.length <= 1) {
            throw new DukeException(ui.getMissingTaskNumMsg());
        }
        int taskDoneIndex;
        try {
            taskDoneIndex = Integer.parseInt(commandArr[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ui.getInvalidNumFormatMsg());
        }
        assert taskDoneIndex >= 0 : "taskDoneIndex must be non-negative";
        if (Command.checkValidTaskNumber(taskDoneIndex, taskLst)) {
            doneTask = taskLst.get(taskDoneIndex);
            doneTask.setDone();
            // Add the UndoableCommand to the stack of commands that can be undone
            // since it is a valid command and has not thrown any errors
            super.execute(tasks, ui, storage);
            // Since the user has made changes to the code,
            // clear the redoStack of all undoable commands
            RedoCommand.redoStack.removeAllElements();
            return ui.getSuccessfulDoneMsg(taskDoneIndex, taskLst);
        } else {
            throw new DukeException(ui.getInvalidTaskNumMsg(taskLst));
        }

    }

    /**
     * Mark the done task as undone.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     */
    public void executeInverse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        doneTask.setUndone();
    }

}

