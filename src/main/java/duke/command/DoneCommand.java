package duke.command;

import duke.exception.IllegalIndexOfTaskException;
import duke.task.Task;
import duke.task.TaskList;

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
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @throws IllegalIndexOfTaskException If the index of the task is out of range.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws IllegalIndexOfTaskException {
        try {
            Task task = tasks.setTaskAtIndexDone(index);
            return new CommandResult(CommandType.Done,tasks.getSize(), task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalIndexOfTaskException("Please provide an valid index of the task.");
        }
    }
}