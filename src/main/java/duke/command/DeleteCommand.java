package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.TaskList;
import duke.task.Task;

/**
 * A DeleteCommand communicates instructions for a task to be deleted.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new DeleteCommand with the given details.
     *
     * @param details   Details of task to be deleted
     */
    public DeleteCommand(String details) {
        super(details);
    }

    /**
     * Removes the task specified by the current DeleteCommand's details from
     * the given task list, then saves the new list to storage.
     *
     * @param tasks             List of tasks
     * @param ui                User interface
     * @param storage           Hard disk storage
     * @throws DukeException    If storage fails, etc.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        int taskIndex = getTaskIndex(details, tasks.size());
        Task deletedTask = tasks.remove(taskIndex);
        ui.showText("Noted. I've removed this task:"
                + "\n  " + deletedTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.");
        save(tasks, storage);
    }
}
