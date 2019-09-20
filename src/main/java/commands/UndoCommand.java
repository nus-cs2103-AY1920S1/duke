package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

import java.util.Stack;

/**
 * UndoCommand is a class that undoes
 * the actions of a user command.
 */
public class UndoCommand extends Command {

    /**
     * The UndoStack that contains all
     * the undoable commands that were executed.
     */
    static Stack<UndoableCommand> undoStack = new Stack<>();

    /**
     * Constructor for UndoCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public UndoCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Displays all tasks that are currently on the
     * list of tasks to the user.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @return String output reply from Duke.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check if there are any commands to undo
        if (undoStack.empty()) {
            return ui.getNothingToUndoMsg();
        }
        // Undo the operations
        UndoableCommand toUndo = undoStack.pop();
        toUndo.executeInverse(tasks, ui, storage);
        // Add the top command in undoStack to the redoStack in order to perform future redo-s
        RedoCommand.redoStack.add(toUndo);
        return ui.getUndoMsg();
    }

}
