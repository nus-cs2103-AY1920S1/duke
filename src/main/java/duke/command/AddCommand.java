package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.WriteFileFailDukeException;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    /**
     * duke.command.Command for duke.TaskList to be added with duke.task.Task on execute.
     *
     * @param task duke.task.Task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes add command which add task to duke.TaskList.
     * Then duke.Storage rewrite using duke.TaskList.
     *
     * @param tasks   duke.TaskList
     * @param ui      duke.Ui
     * @param storage duke.Storage
     * @throws WriteFileFailDukeException On problems with duke.Storage writing
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WriteFileFailDukeException {
        tasks.add(task);
        storage.rewrite(tasks.getSerialized());
        ui.show("Got it. I've added this task:\n  " + task + "Now you have " + tasks.size() + " tasks in the list.");
    }
}
