package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {

    private int taskIndex;

    /**
     * Creates an DeleteCommand instance with an input integer.
     * @param index index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.taskIndex = index;
    }

    /**
     * Deletes a task with a given index and prints out the response when the user deletes a task from the list.
     *
     * @param storage the storage storing task data.
     * @param tasks the list of tasks.
     * @return String representing the response.
     * @throws DukeException self-defined exceptions caused by illegal input.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        try {
            Task task = tasks.delete(taskIndex);
            storage.writeToFile(tasks.generateInfo());
            return "Noted. I've removed this task: \n       " + task
                + "\n     Now you have " + tasks.getSize() + " tasks in the list.";
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
