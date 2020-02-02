package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.task.Task;
import duke.task.TaskList;
import duke.filter.Filter;

import java.util.ArrayList;

/**
 * A class representing a done command.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    private Filter filter;

    /**
     * Constructor specifying a task filter to filter tasks to be set as done.
     * @param filter a task filter to filter tasks to be set as done.
     */
    public DoneCommand(Filter filter) {
        this.filter = filter;
    }

    /**
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws IllegalDescriptionException {
        ArrayList<Task> doneTasks = filter.filter(tasks);
        if (doneTasks.isEmpty()) {
            throw new IllegalDescriptionException("No task satisfies the condition.");
        }
        for (Task task: doneTasks) {
            task.setDone();
        }
        return new CommandResult(CommandType.Done, doneTasks);
    }
}