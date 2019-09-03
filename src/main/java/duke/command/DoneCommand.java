package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.task.Task;

/**
 * Create a DoneCommand. It saves user's task as done.
 */
public class DoneCommand extends Command {
    public DoneCommand(int n) {
        super.pos = n;
    }

    /**
     * Saves user's task as done.
     *
     * @param t TaskList to be appended.
     * @param ui UI to interact with user.
     * @param storage Storage to read and write files.
     * @throws DukeException If user input a number that is not within TaskList.
     */
    public String execute(TaskList t, Ui ui, Storage storage) throws DukeException {
        String message = "";
        try {
            Task doneTask = t.tasks.get(pos).markAsDone();
            message = ui.showDoneTask(doneTask);
            storage.save(t.tasks);
            return message;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input a number that is within the list");
        }
    }
}
