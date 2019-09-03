package duke.command;

import java.io.IOException;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Command to delete tasks from the task list.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates a DeleteCommand object with the specified task number to be deleted assigned.
     *
     * @param command Contains the index of the task to be deleted.
     * @throws NumberFormatException If index of task to be deleted is not a number.
     */
    public DeleteCommand(String command) throws NumberFormatException {
        try {
            this.taskNumber = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new DukeException("    OOPS!!! Please specify the index of the task to be deleted.");
        }
    }

    /**
     * Parses the command given to Duke and creates a DeleteCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return DeleteCommand object to be created.
     * @throws DukeException If no index is given.
     */
    public static DeleteCommand process(String[] fullCommand) throws DukeException {
        if (fullCommand.length == 1) {
            throw new DukeException("    OOPS!!! Please specify the index of the task to be deleted.");
        }
        return new DeleteCommand(fullCommand[1]);
    }

    /**
     * Executes the delete command.
     *
     * @param tasks Task list where the task should be deleted from.
     * @param ui User interface that assists with printing.
     * @param storage Storage to be updated with the task deleted.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.deleteTask(taskNumber, ui);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("    OOPS!!! Your specified task number is out of range.");
        }
        try {
            storage.store(tasks);
        } catch (IOException e) {
            System.err.println("    OOPS!!! " + e.getMessage());
        }
    }
}
