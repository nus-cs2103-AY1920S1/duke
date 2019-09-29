package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a DeleteCommand object in charge of deleting the target task from the list.
 */

public class DeleteCommand extends Command {

    private final int[] indices;

    /**
     * Creates a DeleteCommand object.
     * @param fullCommand String of full, valid command
     * @param indices indices of tasks to be deleted.
     */
    public DeleteCommand(String fullCommand, int[] indices) {
        super(fullCommand);
        this.indices = indices;
    }

    @Override
    /**
     * Deletes the task with given index from the list and updates the storage file.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage the storage object that deals with saving and loading task lists.
     * @return String of duke's response message.
     * @throws DukeException when storage file is not found.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String message = tasks.deleteTask(indices);
        storage.updateFile(tasks);
        return message;
    }
}
