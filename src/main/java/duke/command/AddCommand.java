package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to add a task.
 */
public class AddCommand extends Command {

    private String taskInfo;

    /**
     * Creates an AddCommand instance with an input task.
     * @param taskInfo string representing a task to be added.
     */
    public AddCommand(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    /**
     * Prints out the response when user adds a new task.
     *
     * @param storage the storage storing task data.
     * @param tasks the list of tasks.
     * @return String representing the response.
     * @throws DukeException self-defined exceptions caused by illegal input.
     */
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        try {
            Task task = tasks.add(taskInfo);
            storage.writeToFile(tasks.generateInfo());
            return "Got it. I've added this task:\n       " + task
                    + "\n     Now you have " + tasks.getSize() + " tasks in the list.";
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
