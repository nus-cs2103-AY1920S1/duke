package duke.command;

import duke.DukeException;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Changes a Task's status to done.
 */
public class DoneCommand extends Command {

    public DoneCommand(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Marks Task specified with its number in the list by the user as done.
     *
     * @param tasks List of existing Tasks.
     * @param ui Ui class that handles printing to user interface.
     * @param storage Storage class that handles writing to save file on hard disk.
     * @throws DukeException If command has invalid format: not "done" followed by a single integer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (inputSplit.length != 2) {
                // Exception if there is no task number or multiple words after "done"
                throw new DukeException(":( OOPS!!! Please specify number of a single task to mark as done.\n");
            }
            int specifiedDone = Integer.parseInt(inputSplit[1]); // throws NumberFormatException if not int
            Task doneTask = tasks.getElement(specifiedDone - 1); // throws IndexOutOfBoundsException if invalid
            doneTask.setDone();
            ui.printDoneNotification(doneTask.toString());

            // Write to file
            storage.overwriteFile(tasks.toArrayList());
        } catch (NumberFormatException ne) {
            ui.printError(":( OOPS!!! Please specify task number as one integer only.\n");
        } catch (IndexOutOfBoundsException ie) {
            ui.printError(":( OOPS!!! Please specify valid task number.\n");
        }
    }
}
