package duke.command;

import duke.exception.DukeExecutionException;
import duke.exception.DukeInvalidCommandException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import static duke.ui.Messages.FIND_MISSING_QUERY;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeExecutionException {
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

        if (matches.size() == 0) {
            ui.findNoMatch();
        } else {
            ui.findMatches(matches);
        }
    }
}
