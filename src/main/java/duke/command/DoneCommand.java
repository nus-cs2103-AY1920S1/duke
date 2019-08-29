package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.IndexFormatDukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    private int index;

    /**
     * duke.command.Command for duke.TaskList to set duke.task.Task at index as done.
     *
     * @param indexString Index String
     * @throws IndexFormatDukeException On unable to parse string
     */
    public DoneCommand(String indexString) throws IndexFormatDukeException {
        try {
            index = Integer.parseInt(indexString.trim());
        } catch (NumberFormatException e) {
            throw new IndexFormatDukeException();
        }
    }

    /**
     * Executes done command which set task, of index parsed by constructor, from duke.TaskList as done.
     * Then duke.Storage rewrite using duke.TaskList.
     *
     * @param tasks   duke.TaskList
     * @param ui      duke.Ui
     * @param storage duke.Storage
     * @throws DukeException On index out of bound or problems with duke.Storage writing
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.setDone(index);
        storage.rewrite(tasks.getSerialized());
        ui.show("Nice! I've marked this task as done:\n" + t);
    }
}
