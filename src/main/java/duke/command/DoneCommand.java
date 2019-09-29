package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.IOException;

/**
 * This is a <code>Command</code> to mark one task done. After the <code>execute</code>, The status, <code>isDone</code>
 * of the target task, specified by the ordering number, will be changed to <code>true</code>.
 */
public class DoneCommand extends Command {

    private int taskNumber;

    /**
     * This is a class constructor specifying the target number.
     *
     * @param taskNumber the order shown in the task list
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a certain <code>Task</code> object as done and shows users the successful execution of marking done. If the
     * specified task number is out of the available range, a <code>DukeException</code> will be thrown.
     *
     * @param taskList {@inheritDoc}
     * @param ui       {@inheritDoc}
     * @param storage  {@inheritDoc}
     * @return a string showing the specified task is marked done
     * @throws DukeException If the specified task number does not existed in the current list
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 0 || taskNumber >= taskList.getTotalTask()) {
            throw new DukeException("The task number is invalid!");
        }
        Task doneTask = taskList.getTaskAt(taskNumber);
        if (doneTask.getStatus()) {
            throw new DukeException("You've already marked this task as done!");
        }
        doneTask.markDone();
        try {
            storage.recordTasks(taskList);
        } catch (IOException e) {
            return ui.showSavingError();
        }
        return ui.showTaskDone(doneTask);
    }

    /**
     * Compares two <code>DoneCommand</code> objects according to their targets.
     *
     * @param obj the object to be compared
     * @return <code>true</code> if two objects are both <code>DoneCommand<></code> and have the same task number;
     *         <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoneCommand) {
            DoneCommand another = (DoneCommand) obj;
            return this.taskNumber == another.taskNumber;
        } else {
            return false;
        }
    }
}
