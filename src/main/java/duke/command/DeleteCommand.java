package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is a <code>Command</code> to delete one task from the task list. After the <code>execute</code>, The target
 * task, specified by the ordering number, will be deleted.
 */
public class DeleteCommand extends Command {

    private int target;

    /**
     * This is a class constructor specifying the target number.
     *
     * @param target the order shown in the task list
     */
    public DeleteCommand(int target) {
        this.target = target;
    }

    /**
     * Deletes a certain <code>Task</code> object from the task list and shows users the successful execution of
     * deleting. If the specified task number is out of the available range, a <code>DukeException</code> will be
     * thrown.
     *
     * @param taskList the task list that provides information about users' current tasks and to be modified
     * @param ui       the <code>Ui</code> object to handle input and output
     * @param storage  the <code>Storage</code> object to load and record data
     * @throws DukeException If the specified number for the task does not existed in the current list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (target < 0 || target >= taskList.getTotalTask()) {
            throw new DukeException("The task number is invalid!");
        }
        Task removedTask = taskList.removeTaskAt(target);
        ui.showTaskDeleted(taskList.getTotalTask(), removedTask);
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
            return this.target == another.target;
        } else {
            return false;
        }
    }
}
