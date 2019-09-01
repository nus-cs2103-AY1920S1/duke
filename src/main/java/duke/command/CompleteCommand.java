package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Message.MESSAGE_DONE;
import static duke.ui.Message.concatLines;

/**
 * Marks a task as done.
 */
public class CompleteCommand extends Command {
    private int taskId;

    /**
     * Constructs a CompleteCommand object given taskId of the task to be completed.
     *
     * @param taskId the 1-based index of the task to be completed
     */
    public CompleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTaskByIndex(this.taskId);
        task.markAsDone();
        storage.save(tasks);
        return concatLines(MESSAGE_DONE, task.getIndentedFormat());
    }
}