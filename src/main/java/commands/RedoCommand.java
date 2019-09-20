package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

import java.util.Stack;

/**
 * RedoCommand is a class that redoes
 * the actions of a user command.
 */
public class RedoCommand extends Command {

    /**
     * The RedoStack that contains all the
     * undoable commands that were executed.
     */
    static Stack<UndoableCommand> redoStack = new Stack<>();

    /**
     * Constructor for RedoCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    public RedoCommand(String[] commandArr) {
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
        // Check if there are commands to redo
        if (redoStack.empty()) {
            return ui.getNothingToRedoMsg();
        }
        // Undo the operations
        UndoableCommand toRedo = redoStack.pop();
        toRedo.execute(tasks, ui, storage);
        // Add the top command in redoStack to the undoStack in order to perform future undo-s
        UndoCommand.undoStack.add(toRedo);
        return ui.getRedoMsg();
    }
}
