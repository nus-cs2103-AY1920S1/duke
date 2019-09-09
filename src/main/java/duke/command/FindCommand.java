package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeInvalidCommandException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import static duke.ui.Messages.FIND_MISSING_QUERY;
import static duke.ui.Messages.FIND_NO_TASKS;
import static duke.ui.Messages.FIND_TASKS;

public class FindCommand extends Command {
    private final String[] queries;

    public FindCommand(final String... queries) {
        this.queries = queries;
    }

    @Override
    protected void check(TaskList tasks) throws DukeInvalidCommandException {
        if (queries == null || queries.length == 0) {
            throw new DukeInvalidCommandException(FIND_MISSING_QUERY);
        }
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeExecutionException {
        check(tasks);

        TaskList matches = new TaskList();
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.getTask(i);
            for (final String query : this.queries) {
                if (task.getDescription().contains(query)) {
                    matches.addTask(task);
                    break;
                }
            }
        }

        CommandResult result = new CommandResult();
        if (matches.size() == 0) {
            result.addMessages(FIND_NO_TASKS);
        } else {
            result.addMessages(FIND_TASKS);
            result.addMessages(matches.toString().split("\\n"));
        }
        return result;
    }
}
