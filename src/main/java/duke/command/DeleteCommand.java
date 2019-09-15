package duke.command;

import duke.DukeException;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Deletes a Task from the list.
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String[] inputSplit) {
        super(inputSplit);
    }

    /**
     * Deletes Task specified with its number in the list by the user.
     *
     * @param tasks List of Tasks to be deleted from.
     * @param ui Ui class that handles printing to user interface.
     * @param storage Storage class that handles writing to save file on hard disk.
     * @throws DukeException If command has invalid format: not "delete" followed by a single integer.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (inputSplit.length != 2) {
                // Exception if there is no task number or multiple words after "delete"
                throw new DukeException(":( OOPS!!! Please specify number of a single task to delete.\n");
            }
            int specifiedDel = Integer.parseInt(inputSplit[1]); // will throw NumberFormatException if not int
            Task delTask = tasks.getElement(specifiedDel - 1); // throws IndexOutOfBoundsException if invalid
            tasks.deleteFromList(specifiedDel - 1);
            ui.printDeleteNotification(delTask.toString(), tasks.getSize());

            // Write to file
            storage.overwriteFile(tasks.toArrayList());
        } catch (NumberFormatException ne) {
            ui.printError(":( OOPS!!! Please specify task number as one integer only.\n");
        } catch (IndexOutOfBoundsException ie) {
            ui.printError(":( OOPS!!! Please specify valid task number.\n");
        }
    }
}
