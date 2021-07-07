package seedu.duke.logic.command;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.commons.exceptions.historyexceptions.CannotUndoException;
import seedu.duke.model.Duke;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that allows the client to undo previous commands.
 * Contains a method that executes commands by reverting to the previous version of a TaskList.
 * Stores information on the Duke instance, so the TaskList of the Duke instance may be reverted.
 */
public class UndoCommand extends Command {

    private Duke duke;
    private int iterations;

    /** Creates an instance of UndoCommand.
     * Stores to attributes of UndoCommand relevant information required to create an UndoCommand.
     * Attribute is a reference to instance of Duke for which to perform undo operation(s).
     * @param duke Duke instance for which to perform undo operation(s).
     * @param iterations Number of times to perform undo.
     * @throws DukeException If initialisation of the Duke instance as an attribute is unsuccessful.
     */
    public UndoCommand(Duke duke, int iterations) throws DukeException {
        this.duke = duke;
        this.iterations = iterations;
    }

    /** Executes an UndoCommand object using information stored in instance attributes.
     * Modifies a given TaskList by reverting to a previous version stored in memory.
     * Saves the reverted TaskList to the given Storage.
     * Displays a message indicating successful undo after undo process has been completed.
     * @param tasks TaskList to be reverted by this command.
     * @param ui Ui with methods that are called to display a message upon successful undo of the task.
     * @param storage Storage that contains saved String representation of the given TaskList and is to be modified
     *                upon successful undo of the previous operation.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "TaskList that is to be displayed is null.";
        TaskList resTasks = tasks;
        try {
            for (int i = 0; i < iterations; i++) {
                try {
                    resTasks = this.duke.getTaskListHistory().undo();
                } catch (CannotUndoException ex) {
                    if (i > 0) {
                        this.duke.setTaskList(tasks);
                        ui.showMaxUndoMessageReached(i);
                        storage.save(resTasks, ui);
                        return;
                    } else {
                        throw ex;
                    }
                }
            }
            this.duke.setTaskList(resTasks);
            ui.showUndoMessage(iterations);
            storage.save(resTasks, ui);
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Execution of command unsuccessful.");
        }
    }
}
