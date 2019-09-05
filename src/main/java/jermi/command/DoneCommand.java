package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Formatter;
import jermi.exception.JermiException;
import jermi.exception.InvalidIndexException;
import jermi.task.Task;

/**
 * A representation of the command for marking a task as completed in the list.
 */
public class DoneCommand extends MutateTaskCommand {
    /**
     * Public constructor for class.
     *
     * @param index Index of the task to be marked as done.
     * @throws JermiException {@link InvalidIndexException}.
     */
    public DoneCommand(String index) throws JermiException {
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
        Task task = taskList.getTask(this.index);
        task.markAsDone();
        storage.taskListToFile();
        return formatter.echo("Nice! I've marked this task as done:", "  " + task);
    }
}
