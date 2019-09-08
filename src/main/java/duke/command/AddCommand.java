package duke.command;

import duke.exception.DukeException;
import duke.history.History;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.Tasklist;
import duke.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * Represents an add command.
 */
public class AddCommand extends UndoableCommand {
    private Task task;

    // Constructor
    private AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Creates a new add command.
     * @param task Task object.
     * @return an AddCommand object.
     */
    public static Command createAddCommand(Task task) {
        return new AddCommand(task);
    } // End method.

    /**
     * Executes the add command.
     * @param tasks List of tasks.
     * @param ui The user interface the user sees.
     * @param storage Stores the user's list of tasks.
     * @param history
     * @throws DukeException when an error occurs during execution.
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, Storage storage, History history) throws DukeException {
        history.addExecutedCommand(this);

        try {
            super.commandOutput = ui.addTaskDialogue(task.toString(), tasks.size() + 1);
            // Size + 1 since the addTask dialogue is being generated before it is added.
            tasks.add(task); // Add after task.toString() to see if there is formatting error.
        } catch (DateTimeParseException e) {
            throw new DukeException("The format of the date and time should be dd/MM/yyyy HHmm!");
        }
    }

    /**
     * Undoes add command.
     * @param tasklist List containing user's tasks.
     */
    @Override
    protected void undo(Tasklist tasklist) {
        tasklist.remove(tasklist.size() - 1);
    }
}
