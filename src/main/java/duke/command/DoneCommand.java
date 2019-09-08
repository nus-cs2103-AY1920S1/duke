package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {

    private int taskIndex;

    /**
     * Creates an DoneCommand instance with an input integer.
     * @param index index of task to be marked done.
     */
    public DoneCommand(int index) {
        this.taskIndex = index;
    }

    /**
     * Marks a task as done and prints out the response.
     *
     * @param storage the storage storing task data.
     * @param tasks the list of tasks.
     * @return String representing the response.
     * @throws DukeException self-defined exceptions caused by illegal input.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        try {
            Task task = tasks.done(taskIndex);
            storage.writeToFile(tasks.generateInfo());
            return "Nice! I've marked this task as done:\n       " + task;
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
