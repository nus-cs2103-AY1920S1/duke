package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

/**
 * This is a <code>Command</code> to delete one task from the task list. After the <code>execute</code>, The target
 * task, specified by the ordering number, will be deleted.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * This is a class constructor specifying the target number.
     *
     * @param taskNumber the order shown in the task list
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a certain <code>Task</code> object from the task list and shows users the successful execution of
     * deleting. If the specified task number is out of the available range, a <code>DukeException</code> will be
     * thrown.
     *
     * @param taskList {@inheritDoc}
     * @param ui       {@inheritDoc}
     * @param storage  {@inheritDoc}
     * @return a string showing the specified task is deleted from the task list
     * @throws DukeException If the specified task number does not existed in the current list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 0 || taskNumber >= taskList.getTotalTask()) {
            throw new DukeException("The task number is invalid!");
        }
        Task removedTask = taskList.removeTaskAt(taskNumber);
        try {
            storage.recordTasks(taskList);
        } catch (IOException e) {
            return ui.showSavingError();
        }
        return ui.showTaskDeleted(taskList.getTotalTask(), removedTask);
    }

    /**
     * Compares two <code>DeleteCommand</code> objects according to their targets.
     *
     * @param obj the object to be compared
     * @return <code>true</code> if two objects are both <code>DeleteCommand<></code> and have the same task number;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            DeleteCommand another = (DeleteCommand) obj;
            return this.taskNumber == another.taskNumber;
        } else {
            return false;
        }
    }
}
