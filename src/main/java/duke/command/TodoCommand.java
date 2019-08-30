package duke.command;

import duke.exception.DukeExecutionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends AddCommand {
    public TodoCommand(final String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeExecutionException {
        check(tasks);
        addTask(new Todo(this.description), tasks, ui, storage);
    }
}
