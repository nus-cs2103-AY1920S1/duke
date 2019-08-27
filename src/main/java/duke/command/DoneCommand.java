package duke.command;

import duke.exception.IllegalIndexOfTaskException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A class representing a done command.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private int index;

    /**
     * Constructor specifying the index of the task which is done.
     * @param index the index of the task which is done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command.
     * @param tasks a list task to work on.
     * @param ui an user interface to show messages.
     * @throws IllegalIndexOfTaskException If the index of the task is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) throws IllegalIndexOfTaskException {
        try {
            Task task = tasks.setTaskAtIndexDone(index);
            ui.showDoneTask(task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalIndexOfTaskException("Please provide an valid index of the task.");
        }
    }
}