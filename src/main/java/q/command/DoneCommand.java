package q.command;

import q.error.NoSuchTaskError;
import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;
import q.task.Task;

/**
 * Handles the marking of Tasks as done.
 */
public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand.
     *
     * @param taskNumber The index of the task to be deleted, starting from 0.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the Task at index taskNumber in tasks as done.
     *
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String representing the Task that was marked as done.
     * @throws NoSuchTaskError If the task does not exist.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskError {
        Task t = tasks.doTask(taskNumber);
        storage.save(tasks);
        return ui.showDoneTask(t);
    }
}
