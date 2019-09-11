package seedu.duke.logic.command;

import seedu.duke.commons.exceptions.DukeException;
import seedu.duke.model.Duke;
import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that allows the client to redo actions by commands that have been reverted by undo.
 * Contains a method that executes commands by reverting to the a version of a TaskList that has previously 
 * been undone.
 * Stores information on the Duke instance, so the TaskList of the Duke instance may be reverted to a state before
 * undo was executed.
 */
public class RedoCommand extends Command {

    private Duke duke;


    /** Creates an instance of RedoCommand.
     * Stores to attributes of RedoCommand relevant information required to create an RedoCommand.
     * Attribute is a reference to instance of Duke for which to perform redo operation.
     * @param duke Duke instance for which to perform redo operation.
     * @throws DukeException If initialisation of the Duke instance as an attribute is unsuccessful.
     */
    public RedoCommand(Duke duke) throws DukeException {
        this.duke = duke;
    }

    /** Executes an RedoCommand object using information stored in instance attributes.
     * Modifies a given TaskList by reverting to a previous version stored in memory before UndoCommand was executed.
     * Saves the reverted TaskList to the given Storage.
     * Displays a message indicating successful redo after redo process has been completed.
     * @param tasks TaskList to be reverted by this command to a state before it was undone.
     * @param ui Ui with methods that are called to display a message upon successful redo of the task.
     * @param storage Storage that contains saved String representation of the given TaskList and is to be modified
     *                upon successful redo of the previous operation.
     * @throws DukeException if execution of the command is unsuccessful.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "TaskList that is to be displayed is null.";
        try {
            tasks = this.duke.getTaskListHistory().redo();
            this.duke.setTaskList(tasks);
            ui.showRedoMessage();
            storage.save(tasks, ui);
        } catch (DukeException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new DukeException("Execution of command unsuccessful.");
        }
    }
}
