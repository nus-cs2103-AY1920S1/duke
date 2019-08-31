package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class representing an add command.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Class constructor specifying the task to be added.
     * @param task the task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command.
     * Add the task to the list.
     * @param tasks a list task to work on.
     * @return
     */
    @Override
    public CommandResult execute(TaskList tasks) {
        tasks.addTask(task);
        return new CommandResult(CommandType.Add, tasks.getSize(), task);
    }
}
