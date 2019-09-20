package commands;

import java.lang.reflect.Array;
import java.util.ArrayList;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

import exceptions.DukeException;

import tasks.Task;

/**
 * DeleteCommand is a class that removes the specified
 * task item from the list of tasks. These items
 * can be ToDo, Event or Deadline tasks.
 */
public class DeleteCommand extends UndoableCommand {

    /** The Task that was deleted following the execution of this delete command. */
    Task deletedTask;
    /** The index of the deletedTask in the list of tasks at the point of deletion. */
    int delTaskIndex;

    /**
     * Constructor for DeleteCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public DeleteCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Removes the specified task from the list of tasks.
     * The number of the task to be removed is specified
     * in the user input.
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
        try {
            delTaskIndex = Integer.parseInt(commandArr[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(ui.getInvalidNumFormatMsg());
        }
        assert delTaskIndex >= 0 : "delTaskIndex must be non-negative";
        if (Command.checkValidTaskNumber(delTaskIndex, taskLst)) {
            deletedTask = taskLst.remove(delTaskIndex);
            // Add the UndoableCommand to the stack of commands that can be undone
            // since it is a valid command and has not thrown any errors
            super.execute(tasks, ui, storage);
            // Since the user has made changes to the code,
            // clear the redoStack of all undoable commands
            RedoCommand.redoStack.removeAllElements();
            return ui.getSuccessfulDeleteMsg(deletedTask, taskLst);
        } else {
            throw new DukeException(ui.getInvalidTaskNumMsg(taskLst));
        }
    }

    /**
     * Adds the deleted task back to the list.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     */
    public void executeInverse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Add the deleted task back to task list
        ArrayList<Task> taskLst = tasks.getTaskLst();
        taskLst.add(delTaskIndex, deletedTask);
    }

}
