package duke.command;

import duke.exception.IllegalIndexOfTaskException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A class representing a delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int index;

    /**
     * Constructor specifying the index of the task to be deleted.
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @throws IllegalIndexOfTaskException If the index of the task is out of range.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws IllegalIndexOfTaskException {
        try {
            Task task = tasks.removeTaskAtIndex(index);
            return new CommandResult(CommandType.Delete, tasks.getSize(), task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalIndexOfTaskException("Please provide an valid index of the task.");
        }
    }
}