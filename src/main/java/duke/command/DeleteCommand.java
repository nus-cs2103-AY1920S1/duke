package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

import java.io.IOException;

/**
 * Represents the deleting of tasks.
 */
public class DeleteCommand extends Command {

    /** Integer representing index of task to be deleted. */
    private int taskNum;

    /**
     * Creates a DeleteCommand object that stores the task number to be deleted.
     * @param num Task number to be deleted.
     */
    public DeleteCommand(int num) {
        this.taskNum = num;
    }

    /**
     * Executes the delete command on a TaskList
     *
     * @param tasks TaskList on which the Command should be executed on.
     * @throws DukeException If the task could not be deleted.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException, IOException {
        String result = tasks.deleteTask(taskNum);
        storage.save(tasks);
        return result;
    }
}
