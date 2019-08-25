package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;
import jermi.exception.InvalidIndexException;
import jermi.task.Task;

/**
 * A representation of the command for deleting task from the list.
 */
public class DeleteCommand extends Command {
    /** Index of the task to be deleted */
    private int index;

    /**
     * Public constructor for class.
     *
     * @param index Index of the task to be deleted.
     * @throws JermiException {@link InvalidIndexException}.
     */
    public DeleteCommand(String index) throws JermiException {
        super();

        try {
            this.index = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param ui UI.
     * @param storage Storage.
     * @throws JermiException JermiException.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException {
        Task task = taskList.getTask(index);
        taskList.remove(index);
        int numOfTasks = taskList.getSize();
        ui.echo("Noted. I've removed this task:"
                , "  " + task
                , String.format("Now you have %d task%s in the list.", numOfTasks, numOfTasks == 1 ? "" : "s"));
        storage.taskListToFile();
    }

    /**
     * Indicates if the program should exit.
     *
     * @return {@code false}.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
