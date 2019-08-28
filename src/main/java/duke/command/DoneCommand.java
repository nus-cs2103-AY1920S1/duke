package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TextUi;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A DoneCommand object contains instructions to mark a task as done or undone.
 */
public class DoneCommand extends Command {

    /**
     * Constructs a DoneCommand with the given details, and a flag to indicate
     * whether the command is intended to mark a task as done or undone.
     *
     * @param details   Details of task to be marked as done/undone
     * @param isDone    Whether the task should be marked as done
     */
    public DoneCommand(String details, boolean isDone) {
        super(details);
        this.isDone = isDone;
    }

    /**
     * Finds the task specified by the current DoneCommand's details, then marks
     * it as done or undone accordingly.
     *
     * @param tasks             List of tasks
     * @param ui                User interface
     * @param storage           Hard disk storage
     * @throws DukeException    If task list fails to be saved to storage, etc.
     */
    @Override
    public void execute(TaskList tasks, TextUi ui, Storage storage) throws
            DukeException {
        int taskIndex = getTaskIndex(details, tasks.size());
        Task selectedTask = tasks.get(taskIndex);
        if (isDone) {
            selectedTask.markAsDone();
            ui.showText("Nice! I've marked this task as done:"
                    + "\n  " + selectedTask.toString());
        } else {
            selectedTask.markAsUndone();
            ui.showText("Oh dear. I've marked this task as undone:"
                    + "\n  " + selectedTask.toString());
        }
        save(tasks, storage);
    }
}
