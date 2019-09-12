package duke.command;

import java.io.IOException;
import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * Command to remove tasks from the task list.
 */
public class RemoveCommand extends Command {

    private int taskNumber;

    /**
     * Creates a RemoveCommand object with the specified task number to be removed assigned.
     *
     * @param command Contains the index of the task to be removed.
     * @throws NumberFormatException If index of task to be removed is not a number.
     */
    public RemoveCommand(String command) throws NumberFormatException {
        try {
            this.taskNumber = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please specify the index of the task to be removed.");
        }
    }

    /**
     * Parses the command given to Duke and creates a RemoveCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return RemoveCommand object to be created.
     * @throws DukeException If no index is given.
     */
    public static RemoveCommand process(String[] fullCommand) throws DukeException {
        if (fullCommand.length == 1) {
            throw new DukeException("OOPS!!! Please specify the index of the task to be removed.");
        }
        return new RemoveCommand(fullCommand[1]);
    }

    /**
     * Executes the remove command.
     *
     * @param tasks Task list where the task should be deleted from.
     * @param storage Storage to be updated with the task removed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(tasks.removeTask(taskNumber));
        } catch (IndexOutOfBoundsException e) {
            sb.append("OOPS!!! Your specified task number is out of range.");
        }
        try {
            storage.store(tasks);
        } catch (IOException e) {
            sb.append("OOPS!!! " + e.getMessage());
        }
        assert(!(sb.toString()).isEmpty());
        return sb.toString();
    }
}
