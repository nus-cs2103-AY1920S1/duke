package jermi.command;

import static jermi.misc.Constant.COMMAND_DELETE_MESSAGE;
import static jermi.misc.Constant.COMMAND_NUMBER_OF_TASKS;
import static jermi.misc.Constant.COMMAND_PLURAL_NOUN;
import static jermi.misc.Constant.COMMAND_SINGULAR_NOUN;
import static jermi.misc.Constant.COMMAND_TASK_FORMAT;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Formatter;
import jermi.exception.JermiException;
import jermi.exception.InvalidIndexException;
import jermi.task.Task;

/**
 * A representation of the command for deleting a task from the list.
 */
public class DeleteCommand extends MutateTaskCommand {
    /**
     * Public constructor for class.
     *
     * @param index Index of the task to be deleted.
     * @throws JermiException {@link InvalidIndexException}.
     */
    public DeleteCommand(String index) throws JermiException {
        super(index);
    }

    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param formatter Formatter.
     * @param storage Storage.
     * @return Output response.
     * @throws JermiException JermiException.
     */
    @Override
    public String execute(TaskList taskList, Formatter formatter, Storage storage) throws JermiException {
        final Task task = taskList.getTask(this.index);
        taskList.remove(this.index);
        int numOfTasks = taskList.getSize();
        storage.taskListToFile();
        assert numOfTasks >= 0 : "numOfTasks should be >= 0";
        return formatter.echo(COMMAND_DELETE_MESSAGE,
                String.format(COMMAND_TASK_FORMAT, task),
                String.format(COMMAND_NUMBER_OF_TASKS, numOfTasks,
                        numOfTasks == 1 ? COMMAND_SINGULAR_NOUN : COMMAND_PLURAL_NOUN));
    }
}
