package duke.command;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;

/**
 * Encapsulates a DoneCommand object in charge of marking the target task as done.
 */

public class DoneCommand extends Command {

    private final int[] indices;

    /**
     * Creates a DoneCommand object.
     * @param fullCommand String of full, valid command
     * @param indices indices of tasks to be marked as done.
     */
    public DoneCommand(String fullCommand, int[] indices) {
        super(fullCommand);
        this.indices = indices;
    }

    @Override
    /**
     * Marks the task with given index as done in the list and updates the storage file.
     * @param tasks TaskList object containing a list of existing tasks.
     * @param storage the storage object that deals with saving and loading task lists.
     * @return String of duke's response message.
     * @throws DukeException when storage file is not found.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String message = tasks.doneTask(indices);
        storage.updateFile(tasks);
        return message;

    }
}
