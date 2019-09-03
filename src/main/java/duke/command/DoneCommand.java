package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is a <code>Command</code> to mark one task done. After the <code>execute</code>, The status, <code>isDone</code>
 * of the target task, specified by the ordering number, will be changed to <code>true</code>.
 */
public class DoneCommand extends Command {

    private int target;

    /**
     * This is a class constructor specifying the target number.
     *
     * @param target the order shown in the task list
     */
    public DoneCommand(int target) {
        this.target = target;
    }

    /**
     * Marks a certain <code>Task</code> object as done and shows users the successful execution of marking done. If the
     * specified task number is out of the available range, a <code>DukeException</code> will be thrown.
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
        Task doneTask = taskList.getTaskAt(target);
        doneTask.markDone();
        ui.showTaskDone(doneTask);
    }

    /**
     * Compares two <code>DoneCommand</code> objects according to their targets.
     *
     * @param obj the object to be compared
     * @return <code>true</code> if two objects are both <code>DoneCommand<></code> and have the same task number;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoneCommand) {
            DoneCommand another = (DoneCommand) obj;
            return this.target == another.target;
        } else {
            return false;
        }
    }
}
