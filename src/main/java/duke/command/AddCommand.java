package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class representing an add command.
 */
public abstract class AddCommand extends Command {
    private Task task;

    /**
     * Class constructor specifying the task to be added.
     * @param task the task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Returns the result of executing the command.
     * Add the task to the list.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        tasks.addTask(task);
        return new CommandResult(CommandType.Add, tasks.getSize(), task);
    }
}
