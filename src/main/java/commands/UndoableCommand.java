package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

/**
 * The UndoableCommand class is a type of command
 * that can be undone or redone. Only commands that add a
 * task, delete a task or mark a task as done are undoable and re-doable.
 *
 */
public abstract class UndoableCommand extends Command {

    /**
     * Constructor for UndoableCommand.
     * Takes in an Array of Strings representing the full command given by the user.
     *
     * @param commandArr String array containing the split text retrieved from user input.
     */
    UndoableCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Adds the UndoableCommand to the stack of commands that can be undone.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @return String output reply from Duke.
     * @throws DukeException  If there is invalid input.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        UndoCommand.undoStack.add(this);
        return null;
    }

    /**
     * Performs the direct operation for an undoable command.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     * @throws DukeException  If there is invalid input.
     */
    public abstract void executeDirect(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Performs the inverse operation for an undoable command.
     *
     * @param tasks the TaskList object storing all recorded Tasks.
     * @param ui the Ui object dealing with user interaction.
     * @param storage the Storage object that reads from and writes to the file.
     */
    public abstract void executeInverse(TaskList tasks, Ui ui, Storage storage);

}
