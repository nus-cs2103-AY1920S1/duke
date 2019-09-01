package duke.command;

import duke.Ui;
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
     * command and an index which make up the initial user input
     * 
     * @param commandArray Array of the command and an index that from initial user
     *                     input
     */

    public DoneCommand(String[] commandArray) {
        String indexString = commandArray[1];
        this.index = Integer.parseInt(indexString);
    }

    /**
     * Marks a task with specified index as completed
     * 
     * @param tasks   List of Tasks
     * @param ui      User Interface displaying the tasks in the TaskList
     * @param storage External storage where the list of tasks is stored
     * @throws DukeException If the index doesn't correspond to a task
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index < 1 || tasks.getSize() < index) {
            throw new DukeException("☹ OOPS!!! There is no available task in the given index.");
        }
        Task doneTask = tasks.getTask(index - 1);
        doneTask.setDone(true);
        ui.showDoneTask(doneTask);
        try {
            storage.updateFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
