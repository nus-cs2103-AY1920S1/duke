package duke.command;

import duke.exception.IllegalDescriptionException;
import duke.filter.Filter;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Optional;

/**
 * A class representing a list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private Optional<Filter> filter;

    /**
     * Class constructor.
     */
    public ListCommand(){
        filter = Optional.<Filter>empty();
    }

    /**
     * Constructor specifying a task filter to filter tasks to be listed.
     * @param filter a task filter to filter tasks to be listed.
     */
    public ListCommand(Filter filter) {
        this.filter = Optional.<Filter>of(filter);
    }

    /**
     * Returns the result of executing the command.
     * @param tasks a list task to work on.
     * @return the result of executing the command.
     */
    @Override
    public CommandResult execute(TaskList tasks) throws IllegalDescriptionException {
        if (filter.isPresent()) {
            ArrayList<Task> tasklist = filter.get().filter(tasks);
            if (tasklist.isEmpty()) {
                throw new IllegalDescriptionException("No task satisfies the condition.");
            }
            return new CommandResult(CommandType.List, tasklist);
        } else {
            return new CommandResult(CommandType.List, tasks.getTasks());
        }
    }
}