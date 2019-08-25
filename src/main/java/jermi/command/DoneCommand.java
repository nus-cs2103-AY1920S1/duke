package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;
import jermi.exception.InvalidIndexException;
import jermi.task.Task;

/**
 * A representation of the command for marking a task as completed in the list.
 */
public class DoneCommand extends Command {
    /** Index of the task to be marked as done */
    private int index;

    /**
     * Public constructor for class.
     *
     * @param index Index of the task to be marked as done.
     * @throws JermiException {@link InvalidIndexException}.
     */
    public DoneCommand(String index) throws JermiException {
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
        Task task = taskList.getTask(this.index);
        task.markAsDone();
        ui.echo("Nice! I've marked this task as done:", "  " + task);
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
