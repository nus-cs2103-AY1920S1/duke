package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String[] queries;

    public FindCommand(final String... queries) {
        this.queries = queries;
    }

    @Override
    protected void check(TaskList tasks) throws DukeException {
        if (queries == null || queries.length == 0) {
            throw new DukeException("Find query cannot be empty");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
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
            ui.showMessage("There are no matching tasks in your list");
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            ui.showIndented(matches.toString().split("\\n"));
        }
    }
}
