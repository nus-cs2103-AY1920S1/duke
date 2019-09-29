package duke.command;

import duke.exception.DukeException;
import duke.exception.TaskNotExistException;
import duke.task.Task;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * DoneCommand used to mark a task.
 */
public class DoneCommand extends Command {
    private String[] oneLine;

    public DoneCommand(String[] oneLine) {
        this.oneLine = oneLine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null;
        assert ui != null;
        assert storage != null;

        int i = Integer.parseInt(oneLine[1].trim());
        int tasksSize = tasks.size();

        if (i <= tasksSize && i > 0) {
            return mark_save(tasks, storage, i);
        } else {
            throw new TaskNotExistException("task does not exist");
        }
    }

    /**
     * print out the message when done command is called and save in txt file.
     *
     * @param tasks   The TaskList that store the task.
     * @param storage The Storage that the command should act on.
     * @param i       The task number need to make as done.
     * @return The output of the done command.
     */
    private String mark_save(TaskList tasks, Storage storage, int i) {
        Task current = tasks.getTaskList().get(i - 1);
        current.markAsDone();
        update(tasks, storage);
        return Ui.frontSpace + "Nice! I've marked this task as done: \n"
                + Ui.frontSpace + "   "
                + current.toString();
    }
}