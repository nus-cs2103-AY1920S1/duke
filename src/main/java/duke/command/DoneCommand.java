package duke.command;

import duke.main.*;
import duke.exception.DukeException;
import duke.task.*;

/**
 * Represents the 'done' command.
 */
public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the indexed task as done and saves this new task list in the hard disk
     *
     * @param storage the Storage object to update the tasks in the file
     * @param ui the Ui object dealing with interactions with the user
     * @param tasks the TaskList object containing the existing list of tasks
     */
    public void execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        try {
            Task doneTask = tasks.getTask(index);
            doneTask.markDone();
            ui.output("Nice! I've marked this task as done: \n" + doneTask.toString());
            storage.writeToFile(tasks);
        }
        catch (Exception e) {
            throw new DukeException(String.format("OOPS!!! Please input a number between 1 and %d after 'done'",
                    tasks.getTasksSize()));
        }
    }

    public boolean isRunning() {
        return true;
    }
}
