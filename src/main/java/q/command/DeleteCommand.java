package q.command;

import q.error.QException;
import q.main.Storage;
import q.main.TaskList;
import q.main.Ui;
import q.task.Task;

/**
 * Handles deletion.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand.
     * @param taskNumber The index of the task to be deleted, starting from 0.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the Task of index taskNumber from tasks.
     * @param tasks   The main TaskList.
     * @param ui      The User Interface being used.
     * @param storage The Storage being used.
     * @return A String representing the deleted task.
     * @throws QException When there is an error in the deletion.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws QException {
        Task t = tasks.deleteTask(taskNumber);
        storage.save(tasks);
        return ui.showDeleteTask(t, tasks.getSize());
    }
}
