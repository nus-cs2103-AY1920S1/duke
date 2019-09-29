package duke.command;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * Represents the actions to execute when the command 'done' is triggered.
 */

public class DoneCommand extends Command {

    int index;

    /**
     * Returns an AddDoneCommand object from the commandArray, an array of the
     * command and an index which make up the initial user input.
     * 
     * @param commandArray Array of the command and an index that from initial user
     *                     input
     */

    public DoneCommand(String[] commandArray) {
        String indexString = commandArray[1];
        //parsing index of the Task that should be marked as done
        this.index = Integer.parseInt(indexString);
    }

    /**
     * Marks a task with specified index as completed.
     * 
     * @param tasks   List of Tasks
     * @param storage External storage where the list of tasks is stored
     * @throws DukeException If the index doesn't correspond to a task
     */

    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (index < 1 || tasks.getSize() < index) {
            throw new DukeException(":( OOPS!!! There is no available task in the given index.");
        }
        Task doneTask = tasks.getTask(index - 1);
        doneTask.setDone(true);
        try {
            storage.updateFile(tasks);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return "Nice! I've marked this task as done:\n"
                + "  " + doneTask;
    }
}
