package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * Command to mark a task as completed.
 */
public class DoneCommand extends Command {

    private int taskNumber;

    /**
     * Creates a DoneCommand object with the specified task number to be marked as completed assigned.
     *
     * @param command Contains the index of the task to be marked as completed.
     * @throws NumberFormatException If index of task to be marked as done is not a number.
     */
    public DoneCommand(String command) throws NumberFormatException {
        try {
            this.taskNumber = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please specify a task number to be marked as done.");
        }
    }

    /**
     * Parses the command given to Duke and creates a DoneCommand if possible.
     *
     * @param fullCommand Full command split by whitespace.
     * @return DoneCommand object to be created.
     * @throws DukeException If no index is given.
     */
    public static DoneCommand process(String[] fullCommand) throws DukeException {
        if (fullCommand.length == 1) {
            throw new DukeException("OOPS!!! Please specify a task number to be marked as done.");
        }
        return new DoneCommand(fullCommand[1]);
    }

    /**
     * Executes the completion of the task.
     *
     * @param tasks Task list where the task will be marked as completed.
     * @param storage Storage to be updated with the task completed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(tasks.completeTask(taskNumber));
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
