package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_DONE;
import static duke.ui.Message.concatLines;

/**
 * Mark a task as done.
 */
public class CompleteCommand extends Command {
    private int taskId;

    public CompleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.get(this.taskId);
        task.markAsDone();
        storage.save(tasks);
        return concatLines(MESSAGE_DONE, task.getIndentedFormat());
    }
}