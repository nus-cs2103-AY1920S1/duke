package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

public class CommandFind extends Command {
    private TaskList results;
    private String query;

    public CommandFind(String query) {
        this.results = new TaskList();
        this.query = query;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.toString().contains(query)) {
                    results.add(task);
                }
            }
        } catch (Exception e) {
            throw new DukeException("Search of task list failed.");
        }

        ui.printSearchResults(results);
    }
}
