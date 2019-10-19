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
    private int index;

    /**
     * Constructs a CompleteCommand object given index of the task to be completed.
     *
     * @param index the 1-based index of the task to be completed
     */
    public CompleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.completeTask(this.index);
        storage.save(tasks);
        tasks.commit();
        return concatLines(MESSAGE_DONE, task.getIndentedFormat());
    }
}