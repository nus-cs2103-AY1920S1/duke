package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.task.Task;
import duke.task.TaskList;
import duke.filter.Filter;

import java.util.ArrayList;

/**
 * A class representing a delete command.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private Filter filter;

    /**
     * Constructor specifying a task filter to filter tasks to be deleted.
     * @param filter a task filter to filter tasks to be deleted.
     */
    public DeleteCommand(Filter filter) {
        this.filter = filter;
    }

    /**
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws IllegalDescriptionException {
        ArrayList<Task> deletedTasks = filter.filter(tasks);
        if (deletedTasks.isEmpty()) {
            throw new IllegalDescriptionException("No task satisfies the condition.");
        }
        for (Task task: deletedTasks) {
            tasks.removeTask(task);
        }
        return new CommandResult(CommandType.Delete, deletedTasks);
    }
}