package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Storage;
import duke.ui.TaskList;
import duke.ui.Ui;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    private String[] oneLine;

    public DeleteCommand(String[] oneLine) {
        assert oneLine != null : "oneLine[] should not be empty";
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

        int tasksSize = tasks.size();
        String deleteMessage1 = " Noted. I've removed this task: \n";
        String deleteMessage2 = " Now you have " + (tasksSize - 1) + " tasks in the list.\n";
        int i = Integer.parseInt(oneLine[1].trim());
        return String.format(
                Ui.frontSpace + deleteMessage1 + Ui.frontSpace + "   %s\n" + Ui.frontSpace + deleteMessage2,
                delete_save(tasks, storage, i));
    }

    /**
     * print out the message when delete command is called and save in txt file.
     *
     * @param tasks   The TaskList that store the task.
     * @param storage The Storage that the command should act on.
     * @param i       The task number need to delete.
     * @return The output of the delete task message.
     */
    private String delete_save(TaskList tasks, Storage storage, int i) throws DukeException {
        Task deleteTask = tasks.deleteTask(i - 1);
        update(tasks, storage);
        return deleteTask.toString();
    }
}
