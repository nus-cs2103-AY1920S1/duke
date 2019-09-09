package duke.command;

import duke.exception.DukeExecutionException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends AddTaskCommand {
    public TodoCommand(final String description) {
        super(description);
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws DukeExecutionException {
        check(tasks);
        return addTask(new Todo(this.description), tasks, storage);
    }
}
