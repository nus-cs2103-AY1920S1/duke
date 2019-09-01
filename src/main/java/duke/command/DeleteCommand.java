package duke.command;

import java.util.Optional;

import duke.task.TaskList;

import duke.util.Storage;
import duke.util.Ui;

/**
 * This class allows a task to be deleted from a <code>TaskList</code> object.
 */
public class DeleteCommand implements Command {
    int taskNum;

    /**
     * Returns a command which allows us to remove a task from <code>taskList</code>.
     *
     * @param taskNum index of <code>task</code> to be removed from <code>taskList</code>
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    // todo handle exceptions
    /**
     * Executes this command by removing task with <code>taskNum</code> from <code>taskList</code>.
     * Informs user that command has been executed.
     *
     * @param taskList task list which the task should be added to
     * @param storage storage which stores all tasks on the local hard disk, if any
     */
    public String execute(TaskList taskList, Optional<Storage> storage) throws IndexOutOfBoundsException {
        taskList.deleteTask(taskNum);
        storage.ifPresent(s -> s.update(taskList.getTasksAsStream()));
        return Ui.informDeleted(taskList.getLastEditedTask(), taskList.getSize());
    }
}
