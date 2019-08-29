package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.IndexFormatDukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int index;

    /**
     * duke.command.Command for duke.TaskList to remove duke.task.Task at index.
     *
     * @param indexString Index String
     * @throws IndexFormatDukeException On unable to parse string
     */
    public DeleteCommand(String indexString) throws IndexFormatDukeException {
        try {
            index = Integer.parseInt(indexString.trim());
        } catch (NumberFormatException e) {
            throw new IndexFormatDukeException();
        }
    }

    /**
     * Executes delete command which remove task, of index parsed by constructor, from duke.TaskList.
     * Then duke.Storage rewrite using duke.TaskList.
     *
     * @param tasks   duke.TaskList
     * @param ui      duke.Ui
     * @param storage duke.Storage
     * @throws DukeException On index out of bound or problems with duke.Storage writing
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.remove(index);
        storage.rewrite(tasks.getSerialized());
        ui.show("Noted. I've removed this task:\n  "
            + t
            + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
