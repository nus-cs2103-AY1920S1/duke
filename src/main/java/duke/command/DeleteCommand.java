package duke.command;

import duke.task.TaskList;

import duke.util.storage.OptionalStorage;
import duke.util.ui.Ui;

/**
 * This class allows a task to be deleted from a <code>TaskList</code> object.
 */
public class DeleteCommand implements Command {
    private final static boolean IS_EXIT = false;
    int taskNum;

    /**
     * Returns a command which allows us to remove a task from <code>taskList</code>.
     *
     * @param taskNum index of <code>task</code> to be removed from <code>taskList</code>
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    
    /**
     * Executes this command by removing task with <code>taskNum</code> from <code>taskList</code>.
     * Informs user that command has been executed.
     *
     * @param taskList task list which the task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public String execute(TaskList taskList, OptionalStorage storage) throws IndexOutOfBoundsException {
        taskList.deleteTask(taskNum);
        storage.update(taskList.getTasksAsStream());
        return Ui.informDeleted(taskList.getLastEditedTask(), taskList.getSize());
    }

    public boolean isExit() {
        return IS_EXIT;
    }
}
